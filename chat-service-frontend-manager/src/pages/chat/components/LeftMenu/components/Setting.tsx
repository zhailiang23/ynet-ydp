import React from 'react';
import { SettingOutlined } from '@ant-design/icons/lib';
import { useSnapshot } from '@umijs/max';
import { ModalForm, ProFormSwitch, ProFormText, ProFormTextArea } from '@ant-design/pro-components';
import { updateChatSetting } from '@/services';
import { App } from 'antd';
import ProFormFileSelect from '@/components/ProFormFileSelect';
import { useBoolean } from 'ahooks';
import { FormInstance } from 'antd/es';
import Wrapper from './Wrapper';
import adminSetting from '@/pages/chat/store/adminSetting';
import userStore from '@/pages/chat/store/users';

const Setting = () => {
  const [open, openAction] = useBoolean(false);
  const { setting } = useSnapshot(adminSetting);
  const { message } = App.useApp();

  const form = React.useRef<FormInstance>();

  React.useEffect(() => {
    if (open && setting) {
      form.current?.setFieldsValue(setting);
    }
  }, [open, setting]);

  return (
    <>
      <Wrapper onClick={openAction.setTrue} active={open}>
        <SettingOutlined />
      </Wrapper>
      <ModalForm
        formRef={form}
        modalProps={{
          zIndex: 99,
        }}
        labelCol={{
          span: 5,
        }}
        open={open}
        title={'基本设置'}
        onOpenChange={openAction.set}
        onFinish={async (data: API.AdminChatSetting) => {
          await updateChatSetting(data);
          message.success('设置成功');
          adminSetting.fetchSetting();
          // 同步更新所有用户卡片的 AI 接管状态
          userStore.updateAllUsersAiEnabled(data.is_ai_enabled);
          return true;
        }}
        layout="horizontal"
      >
        <ProFormText name={'name'} label={'客服名称'} rules={[{ max: 8, required: true }]} />
        <ProFormFileSelect name={'avatar'} label={'头像'} />
        <ProFormSwitch
          name={'is_auto_accept'}
          label={'是否自动接入'}
          tooltip={'开启后当用户咨询时会自动接入'}
        />
        <ProFormSwitch
          name={'is_ai_enabled'}
          label={'是否全局AI托管'}
          tooltip={'开启后所有新接入的用户会话将自动使用AI回复'}
        />
        <ProFormTextArea
          fieldProps={{ autoSize: true }}
          rules={[{ max: 512 }]}
          name={'welcome_content'}
          label={'欢迎语'}
          tooltip={'接入用户后自动发送的欢迎语'}
        />
      </ModalForm>
    </>
  );
};
export default Setting;
