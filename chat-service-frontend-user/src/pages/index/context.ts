import React from "react";

// 留资表单来源参数
export interface CollectFormParams {
  templateId?: number
  adminId?: number
}

const context = React.createContext<{
  send: ((a: APP.Action) => Promise<boolean>) | undefined,
  is_show_queue?: boolean,
  is_show_read?: boolean,
  showCollectForm?: (params?: CollectFormParams) => void,
  showCollectFormB?: (params?: CollectFormParams) => void
}>({
  send: undefined,
  is_show_queue: false,
  is_show_read: false,
  showCollectForm: undefined,
  showCollectFormB: undefined
})

export default context
