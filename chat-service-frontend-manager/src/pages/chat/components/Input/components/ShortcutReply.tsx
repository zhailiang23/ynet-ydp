import React from 'react';
import { UnorderedListOutlined } from '@ant-design/icons/lib';
import { App, List, Popover, Typography } from 'antd';
import { useModel, useRequest, useSnapshot } from '@umijs/max';
import { getAutoMessage } from '@/services/auto';
import websocket from '../../../store/websocket';

const ShortcutReply = () => {
  const { data } = useRequest(() => getAutoMessage({ current: 1, pageSize: 100 }));
  const { initialState } = useModel('@@initialState');

  const { send } = useSnapshot(websocket);

  const { message } = App.useApp();

  // 为 URL 追加参数的辅助函数
  const appendUrlParams = (url: string, params: Record<string, string | number>) => {
    const separator = url.includes('?') ? '&' : '?';
    const paramString = Object.entries(params)
      .map(([key, value]) => `${key}=${value}`)
      .join('&');
    return `${url}${separator}${paramString}`;
  };

  const getContent = React.useCallback((msg: API.AutoMessage) => {
    switch (msg.type) {
      case 'text':
        return msg.content;
      case 'navigator': {
        if (msg.navigator) {
          // 为卡片类型的 URL 追加 templateId 和 adminId 参数
          const adminId = initialState?.currentUser?.id;
          let url = msg.navigator.url;
          if (adminId) {
            url = appendUrlParams(url, {
              templateId: msg.id,
              adminId: adminId,
            });
          }
          return JSON.stringify({
            title: msg.navigator.title,
            url: url,
            image: msg.navigator.image?.url,
          });
        }
        return '';
      }
      case 'video':
      case 'audio':
      case 'image':
      case 'pdf':
        if (msg.file) {
          return msg.file.url;
        }
        return '';
      default: {
        return '';
      }
    }
  }, [initialState?.currentUser?.id]);

  return (
    <Popover
      placement={'right'}
      trigger={['click']}
      overlayInnerStyle={{
        padding: 0,
      }}
      content={
        <div className={'w-96'}>
          <List<API.AutoMessage>
            dataSource={data}
            size={'small'}
            renderItem={(item, index) => {
              return (
                <List.Item
                  className={'cursor-pointer hover:bg-stone-100'}
                  onClick={() => {
                    const content = getContent(item);
                    if (content === '') {
                      message.error('该快捷回复无内容').then();
                    } else {
                      send(content, item.type);
                    }
                  }}
                >
                  <Typography.Text ellipsis={true}>
                    {index + 1}.{`${item.name}`}
                  </Typography.Text>
                </List.Item>
              );
            }}
            bordered={true}
          />
        </div>
      }
    >
      <UnorderedListOutlined className={'cursor-pointer'} />
    </Popover>
  );
};
export default ShortcutReply;
