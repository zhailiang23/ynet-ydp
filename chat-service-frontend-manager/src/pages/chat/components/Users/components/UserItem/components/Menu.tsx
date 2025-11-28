import React from 'react';
import { App, Space, Tooltip } from 'antd';
import { useSnapshot } from '@umijs/max';
import useRemoveUser from '@/pages/chat/hooks/useRemoveUser';
import { removeUser, updateUserAiEnabled } from '@/services';
import { MessageOutlined, CloseOutlined, SwapOutlined, RobotOutlined } from '@ant-design/icons';
import { When } from 'react-if';
import transfer from '@/pages/chat/store/transfer';
import historySession from '@/pages/chat/store/historySession';
import userStore from '@/pages/chat/store/users';

const Menu: React.FC<{
  user: API.User;
}> = (props) => {
  const { setUser, setVisible } = useSnapshot(transfer);

  const { modal, message } = App.useApp();

  const sessionStore = useSnapshot(historySession);
  const { updateUser } = useSnapshot(userStore);

  const handleRemove = useRemoveUser();

  const handleDelete = React.useCallback(
    (user: API.User) => {
      if (user.disabled) {
        removeUser(user.id).then(() => {
          handleRemove(user);
        });
      } else {
        modal.confirm({
          title: '提示',
          content: '确定断开与该用户的会话?',
          onOk() {
            removeUser(user.id).then(() => {
              handleRemove(user);
            });
          },
        });
      }
    },
    [handleRemove, modal],
  );

  const handleToggleAi = React.useCallback(
    (user: API.User) => {
      const newAiEnabled = !user.ai_enabled;
      updateUserAiEnabled(user.id, newAiEnabled).then((res) => {
        if (res.success) {
          updateUser(user.id, { ai_enabled: newAiEnabled });
          message.success(newAiEnabled ? 'AI 接管已开启' : 'AI 接管已关闭');
        }
      });
    },
    [updateUser, message],
  );

  return (
    <div className={'w-full text-right text-sm leading-3'}>
      <Space size={'small'}>
        <When condition={!props.user.disabled}>
          <Tooltip title={props.user.ai_enabled ? '关闭 AI 接管' : '开启 AI 接管'}>
            <RobotOutlined
              style={{ color: props.user.ai_enabled ? '#1890ff' : undefined }}
              onClick={(e) => {
                handleToggleAi(props.user);
                e.stopPropagation();
              }}
            />
          </Tooltip>
        </When>
        <MessageOutlined
          onClick={(e) => {
            sessionStore.show(props.user.id);
            e.stopPropagation();
          }}
        />
        <When condition={!props.user.disabled}>
          <SwapOutlined
            onClick={(e) => {
              setUser(props.user);
              setVisible(true);
              e.stopPropagation();
            }}
          />
        </When>
        <CloseOutlined
          onClick={(e) => {
            handleDelete(props.user);
            e.stopPropagation();
          }}
        />
      </Space>
    </div>
  );
};
export default Menu;
