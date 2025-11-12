import { httpClient } from "./request"
import type { LoginReq, LoginResp } from "../types/course"

/**
 * 登录
 */
export async function login(data: LoginReq): Promise<LoginResp> {
  // 使用 admin-api 的登录接口
  return httpClient.post<LoginResp>("/system/auth/login", data)
}
