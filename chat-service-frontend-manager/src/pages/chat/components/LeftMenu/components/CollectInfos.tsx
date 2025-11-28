import React from 'react';
import { Drawer, Table, Tag, Button, Badge, Modal, Card, Avatar, Timeline, message, Spin } from 'antd';
import type { ColumnsType } from 'antd/es/table';
import Wrapper from './Wrapper';
import { FileTextFilled, RightOutlined, ClockCircleOutlined, CheckCircleOutlined, SyncOutlined, UserOutlined, FileTextOutlined, ArrowLeftOutlined } from '@ant-design/icons';
import { getCollectInfos, getCollectInfoDetail, updateCollectInfo } from '@/services';

const CollectInfos = () => {
  const [visible, setVisible] = React.useState(false);
  const [loading, setLoading] = React.useState(false);
  const [data, setData] = React.useState<API.CollectInfo[]>([]);
  const [pendingCount, setPendingCount] = React.useState(0);

  // 详情模态框状态
  const [detailVisible, setDetailVisible] = React.useState(false);
  const [detailLoading, setDetailLoading] = React.useState(false);
  const [detailInfo, setDetailInfo] = React.useState<API.CollectInfo | null>(null);
  const [updating, setUpdating] = React.useState(false);

  const fetchData = async () => {
    setLoading(true);
    try {
      const res = await getCollectInfos();
      if (res.success) {
        setData(res.data || []);
        // 统计待处理数量 (状态 0 和 3)
        const pending = (res.data || []).filter((item) => item.status === 0 || item.status === 3).length;
        setPendingCount(pending);
      }
    } catch (e) {
      console.error('获取留资信息失败', e);
    } finally {
      setLoading(false);
    }
  };

  // 组件挂载时获取未处理数量
  React.useEffect(() => {
    fetchData();
  }, []);

  const handleOpen = () => {
    setVisible(true);
    fetchData();
  };

  // 打开详情模态框
  const openDetail = async (record: API.CollectInfo) => {
    setDetailVisible(true);
    setDetailLoading(true);
    try {
      const res = await getCollectInfoDetail(record.id);
      if (res.success) {
        setDetailInfo(res.data);
      }
    } catch (e) {
      console.error('获取留资详情失败', e);
      message.error('获取详情失败');
    } finally {
      setDetailLoading(false);
    }
  };

  // 关闭详情模态框
  const closeDetail = () => {
    setDetailVisible(false);
    setDetailInfo(null);
  };

  // 刷新详情
  const refreshDetail = async () => {
    if (!detailInfo) return;
    setDetailLoading(true);
    try {
      const res = await getCollectInfoDetail(detailInfo.id);
      if (res.success) {
        setDetailInfo(res.data);
      }
    } catch (e) {
      console.error('刷新详情失败', e);
    } finally {
      setDetailLoading(false);
    }
  };

  // 获取状态 Tag
  const getStatusTag = (status: number, statusText?: string) => {
    const config: Record<number, { color: string; text: string; icon: React.ReactNode }> = {
      0: { color: 'orange', text: '待处理', icon: <ClockCircleOutlined /> },
      1: { color: 'green', text: '已完成', icon: <CheckCircleOutlined /> },
      2: { color: 'default', text: '已忽略', icon: null },
      3: { color: 'processing', text: '处理中', icon: <SyncOutlined spin /> },
    };
    const cfg = config[status] || config[0];
    return <Tag color={cfg.color} icon={cfg.icon}>{statusText || cfg.text}</Tag>;
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

  // 解析内容
  const parseContent = (content: string) => {
    try {
      return JSON.parse(content);
    } catch {
      return { content };
    }
  };

  // 处理开始处理
  const handleStartProcess = async () => {
    if (!detailInfo) return;
    setUpdating(true);
    try {
      const res = await updateCollectInfo(detailInfo.id, { status: 3 });
      if (res.success) {
        message.success('已开始处理');
        refreshDetail();
        fetchData(); // 刷新列表
      }
    } catch (e) {
      message.error('操作失败');
    } finally {
      setUpdating(false);
    }
  };

  // 处理标记完成
  const handleComplete = async () => {
    if (!detailInfo) return;
    setUpdating(true);
    try {
      const res = await updateCollectInfo(detailInfo.id, { status: 1 });
      if (res.success) {
        message.success('已标记完成');
        refreshDetail();
        fetchData(); // 刷新列表
      }
    } catch (e) {
      message.error('操作失败');
    } finally {
      setUpdating(false);
    }
  };

  // 构建时间线数据
  const getTimelineItems = () => {
    if (!detailInfo) return [];
    const items = [];

    // 创建时间
    if (detailInfo.create_time) {
      items.push({
        color: 'green',
        children: (
          <div>
            <div className="font-medium">创建留资记录</div>
            <div className="text-gray-400 text-sm">{detailInfo.create_time}</div>
          </div>
        ),
      });
    }

    // 开始处理时间
    if (detailInfo.accept_time) {
      items.push({
        color: 'blue',
        children: (
          <div>
            <div className="font-medium">开始处理</div>
            <div className="text-gray-400 text-sm">{detailInfo.accept_time}</div>
          </div>
        ),
      });
    } else if (detailInfo.status === 0) {
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
    if (detailInfo.finish_time) {
      items.push({
        color: 'green',
        children: (
          <div>
            <div className="font-medium">处理完成</div>
            <div className="text-gray-400 text-sm">{detailInfo.finish_time}</div>
          </div>
        ),
      });
    } else if (detailInfo.status !== 2 && detailInfo.status !== 1) {
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
    if (!detailInfo) return null;
    // 已完成和已忽略状态不显示按钮
    if (detailInfo.status === 1 || detailInfo.status === 2) return null;

    if (detailInfo.status === 0) {
      return (
        <Button type="primary" loading={updating} onClick={handleStartProcess}>
          开始处理
        </Button>
      );
    }

    if (detailInfo.status === 3) {
      return (
        <Button type="primary" loading={updating} onClick={handleComplete}>
          标记完成
        </Button>
      );
    }

    return null;
  };

  const columns: ColumnsType<API.CollectInfo> = [
    {
      dataIndex: 'username',
      title: '提交用户',
      width: 100,
    },
    {
      dataIndex: 'template_name',
      title: '留资模板',
      width: 120,
      render: (val) => val || '-',
    },
    {
      dataIndex: 'status',
      title: '留资状态',
      width: 100,
      render: (val, record) => getStatusTag(val, record.status_text),
    },
    {
      dataIndex: 'create_time',
      title: '提交时间',
      width: 150,
    },
    {
      dataIndex: 'accept_time',
      title: '受理时间',
      width: 150,
      render: (val) => val || '-',
    },
    {
      dataIndex: 'finish_time',
      title: '完成时间',
      width: 150,
      render: (val) => val || '-',
    },
    {
      dataIndex: 'admin_name',
      title: '处理客服',
      width: 100,
      render: (val) => val || '-',
    },
    {
      title: '操作',
      width: 80,
      render: (_, record) => {
        return (
          <Button
            type="link"
            size="small"
            icon={<RightOutlined />}
            onClick={() => openDetail(record)}
          >
            查看
          </Button>
        );
      },
    },
  ];

  return (
    <>
      <Wrapper onClick={handleOpen} active={visible}>
        <Badge count={pendingCount} size="small" offset={[-2, 2]}>
          <FileTextFilled style={{ fontSize: 20 }} />
        </Badge>
      </Wrapper>
      <Drawer
        title="留资信息"
        open={visible}
        placement={'right'}
        styles={{
          body: {
            padding: 0,
          },
        }}
        width={900}
        onClose={(e) => {
          setVisible(false);
          e.stopPropagation();
        }}
      >
        <Table<API.CollectInfo>
          rowKey={'id'}
          size={'small'}
          loading={loading}
          dataSource={data}
          pagination={{ pageSize: 10 }}
          columns={columns}
        />
      </Drawer>

      {/* 详情模态框 */}
      <Modal
        title={
          <div className="flex items-center gap-2">
            <ArrowLeftOutlined className="cursor-pointer" onClick={closeDetail} />
            <span>留资处理</span>
          </div>
        }
        open={detailVisible}
        onCancel={closeDetail}
        footer={null}
        width={900}
        styles={{
          body: {
            maxHeight: '70vh',
            overflowY: 'auto',
          },
        }}
      >
        <Spin spinning={detailLoading}>
          {detailInfo && (
            <div style={{ display: 'flex', flexDirection: 'column', gap: 16 }}>
              {/* 头部信息 */}
              <Card size="small">
                <div className="flex justify-between items-center">
                  <div style={{ display: 'flex', alignItems: 'center', gap: 16 }}>
                    <Avatar size={48} icon={<UserOutlined />} />
                    <div>
                      <div className="flex items-center gap-2">
                        <span className="text-lg font-medium">{detailInfo.username || '未知用户'}</span>
                        <span className="text-gray-400 text-sm">{getSourceText(detailInfo.source || '')}</span>
                      </div>
                      <div style={{ display: 'flex', alignItems: 'center', gap: 12, marginTop: 4 }}>
                        <FileTextOutlined style={{ color: '#1890ff' }} />
                        <span style={{ color: '#666' }}>{detailInfo.template_name || '留资信息'}</span>
                        {getStatusTag(detailInfo.status)}
                      </div>
                    </div>
                  </div>
                  <div>
                    {renderActionButton()}
                  </div>
                </div>
              </Card>

              {/* 卡片区域：左侧留资类型+详细信息，右侧状态历史 */}
              <div style={{ display: 'grid', gridTemplateColumns: '2fr 1fr', gap: 16 }}>
                {/* 左侧：留资类型 + 详细信息 */}
                <div style={{ display: 'flex', flexDirection: 'column', gap: 16 }}>
                  {/* 卡片1: 留资类型 */}
                  <Card title="留资类型" size="small">
                    <div style={{ display: 'flex', flexDirection: 'column', gap: 16 }}>
                      <div>
                        <div style={{ fontWeight: 600, color: '#666', fontSize: 13, marginBottom: 4 }}>类型名称</div>
                        <div>{detailInfo.template_name || '-'}</div>
                      </div>
                      <div style={{ display: 'flex', gap: 48 }}>
                        <div>
                          <div style={{ fontWeight: 600, color: '#666', fontSize: 13, marginBottom: 4 }}>创建时间</div>
                          <div>{detailInfo.create_time || '-'}</div>
                        </div>
                        <div>
                          <div style={{ fontWeight: 600, color: '#666', fontSize: 13, marginBottom: 4 }}>最后更新</div>
                          <div>{detailInfo.update_time || detailInfo.create_time || '-'}</div>
                        </div>
                      </div>
                    </div>
                  </Card>

                  {/* 卡片2: 详细信息 */}
                  <Card title="详细信息" size="small">
                    <div style={{ display: 'flex', flexDirection: 'column', gap: 12 }}>
                      {detailInfo.content && (() => {
                        const parsed = parseContent(detailInfo.content);
                        return Object.entries(parsed).map(([key, value]) => (
                          <div key={key} style={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center' }}>
                            <span style={{ color: '#666', fontSize: 14 }}>{key}：</span>
                            <span style={{ color: '#333', fontSize: 14 }}>{String(value)}</span>
                          </div>
                        ));
                      })()}
                      {detailInfo.remark && (
                        <div style={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center' }}>
                          <span style={{ color: '#666', fontSize: 14 }}>备注：</span>
                          <span style={{ color: '#333', fontSize: 14 }}>{detailInfo.remark}</span>
                        </div>
                      )}
                    </div>
                  </Card>
                </div>

                {/* 右侧：状态历史 */}
                <Card title="状态历史" size="small">
                  <Timeline items={getTimelineItems()} />
                </Card>
              </div>
            </div>
          )}
        </Spin>
      </Modal>
    </>
  );
};

export default CollectInfos;
