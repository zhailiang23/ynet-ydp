import React from 'react';
import { PageContainer, ProCard } from '@ant-design/pro-components';
import { useParams, useRequest, history } from '@umijs/max';
import { getCollectInfoDetail, updateCollectInfo } from '@/services';
import { Avatar, Button, Card, Tag, Timeline, message, Spin } from 'antd';
import { ArrowLeftOutlined, UserOutlined, FileTextOutlined, ClockCircleOutlined, CheckCircleOutlined, SyncOutlined } from '@ant-design/icons';
import { When } from 'react-if';

const CollectInfoDetail = () => {
  const { id = '0' } = useParams<{ id: string }>();
  const [updating, setUpdating] = React.useState(false);

  const { data, loading, refresh } = useRequest(() => getCollectInfoDetail(Number(id)));
  const info = data;

  // 解析内容
  const parseContent = (content: string) => {
    try {
      return JSON.parse(content);
    } catch {
      return { content };
    }
  };

  // 获取状态对应的 Tag
  const getStatusTag = (status: number) => {
    const statusConfig: Record<number, { color: string; text: string; icon: React.ReactNode }> = {
      0: { color: 'orange', text: '未处理', icon: <ClockCircleOutlined /> },
      1: { color: 'processing', text: '处理中', icon: <SyncOutlined spin /> },
      2: { color: 'green', text: '处理完成', icon: <CheckCircleOutlined /> },
    };
    const config = statusConfig[status] || statusConfig[0];
    return <Tag color={config.color} icon={config.icon}>{config.text}</Tag>;
  };

  // 获取来源显示文本
  const getSourceText = (source: string) => {
    const sourceMap: Record<string, string> = {
      'weapp': '来自小程序',
      'app': '来自APP',
      'h5': '来自H5',
      'web': '来自网页',
      'wecom': '来自企业微信',
    };
    return sourceMap[source] || source || '来自聊天';
  };

  // 处理开始处理
  const handleStartProcess = async () => {
    if (!info) return;
    setUpdating(true);
    try {
      const res = await updateCollectInfo(info.id, { status: 1 });
      if (res.success) {
        message.success('已开始处理');
        refresh();
      }
    } catch (e) {
      message.error('操作失败');
    } finally {
      setUpdating(false);
    }
  };

  // 处理标记完成
  const handleComplete = async () => {
    if (!info) return;
    setUpdating(true);
    try {
      const res = await updateCollectInfo(info.id, { status: 2 });
      if (res.success) {
        message.success('已标记完成');
        refresh();
      }
    } catch (e) {
      message.error('操作失败');
    } finally {
      setUpdating(false);
    }
  };

  // 构建时间线数据
  const getTimelineItems = () => {
    if (!info) return [];
    const items = [];

    // 创建时间
    if (info.create_time) {
      items.push({
        color: 'green',
        children: (
          <div>
            <div className="font-medium">创建留资记录</div>
            <div className="text-gray-400 text-sm">{info.create_time}</div>
          </div>
        ),
      });
    }

    // 开始处理时间
    if (info.accept_time) {
      items.push({
        color: 'blue',
        children: (
          <div>
            <div className="font-medium">开始处理</div>
            <div className="text-gray-400 text-sm">{info.accept_time}</div>
          </div>
        ),
      });
    } else if (info.status === 0) {
      items.push({
        color: 'gray',
        children: (
          <div>
            <div className="text-gray-400">开始处理</div>
            <div className="text-gray-300 text-sm">等待处理</div>
          </div>
        ),
      });
    }

    // 处理完成时间
    if (info.finish_time) {
      items.push({
        color: 'green',
        children: (
          <div>
            <div className="font-medium">处理完成</div>
            <div className="text-gray-400 text-sm">{info.finish_time}</div>
          </div>
        ),
      });
    } else if (info.status !== 2 && info.status !== 1) {
      items.push({
        color: 'gray',
        children: (
          <div>
            <div className="text-gray-400">处理完成</div>
            <div className="text-gray-300 text-sm">等待完成</div>
          </div>
        ),
      });
    }

    return items;
  };

  // 渲染操作按钮
  const renderActionButton = () => {
    if (!info) return null;
    // 处理完成状态不显示按钮
    if (info.status === 2) return null;

    if (info.status === 0) {
      return (
        <Button type="primary" loading={updating} onClick={handleStartProcess}>
          开始处理
        </Button>
      );
    }

    if (info.status === 1) {
      return (
        <Button type="primary" loading={updating} onClick={handleComplete}>
          标记完成
        </Button>
      );
    }

    return null;
  };

  return (
    <PageContainer
      loading={loading}
      header={{
        title: (
          <div className="flex items-center gap-2 cursor-pointer" onClick={() => history.back()}>
            <ArrowLeftOutlined />
            <span>留资处理</span>
          </div>
        ),
      }}
    >
      <When condition={!!info}>
        {() => (
          <div className="space-y-4">
            {/* 头部信息 */}
            <Card>
              <div className="flex justify-between items-center">
                <div className="flex items-center gap-4">
                  <Avatar size={48} icon={<UserOutlined />} />
                  <div>
                    <div className="flex items-center gap-2">
                      <span className="text-lg font-medium">{info?.username || '未知用户'}</span>
                      <span className="text-gray-400 text-sm">{getSourceText(info?.source || '')}</span>
                    </div>
                    <div className="flex items-center gap-2 mt-1">
                      <FileTextOutlined className="text-blue-500" />
                      <span className="text-gray-500">{info?.template_name || '留资信息'}</span>
                      {getStatusTag(info?.status || 0)}
                    </div>
                  </div>
                </div>
                <div>
                  {renderActionButton()}
                </div>
              </div>
            </Card>

            {/* 三个卡片 */}
            <div className="grid grid-cols-1 md:grid-cols-3 gap-4">
              {/* 卡片1: 留资类型 */}
              <Card title="留资类型" size="small">
                <div className="space-y-3">
                  <div>
                    <div className="text-gray-400 text-sm">类型名称</div>
                    <div className="font-medium">{info?.template_name || '-'}</div>
                  </div>
                  <div>
                    <div className="text-gray-400 text-sm">类型描述</div>
                    <div className="text-gray-600">{info?.template_description || '-'}</div>
                  </div>
                  <div>
                    <div className="text-gray-400 text-sm">创建时间</div>
                    <div>{info?.create_time || '-'}</div>
                  </div>
                  <div>
                    <div className="text-gray-400 text-sm">最后更新</div>
                    <div>{info?.update_time || info?.create_time || '-'}</div>
                  </div>
                </div>
              </Card>

              {/* 卡片2: 详细信息 */}
              <Card title="详细信息" size="small">
                <div className="space-y-3">
                  {info?.content && (() => {
                    const parsed = parseContent(info.content);
                    return Object.entries(parsed).map(([key, value]) => (
                      <div key={key}>
                        <div className="text-gray-400 text-sm">{key}</div>
                        <div className="font-medium">{String(value)}</div>
                      </div>
                    ));
                  })()}
                  {info?.remark && (
                    <div>
                      <div className="text-gray-400 text-sm">备注</div>
                      <div>{info.remark}</div>
                    </div>
                  )}
                </div>
              </Card>

              {/* 卡片3: 状态历史 */}
              <Card title="状态历史" size="small">
                <Timeline items={getTimelineItems()} />
              </Card>
            </div>
          </div>
        )}
      </When>
    </PageContainer>
  );
};

export default CollectInfoDetail;
