import dayjs from 'dayjs';

/**
 * 证件号码脱敏
 * 保留前4位和后4位,中间显示为 ****
 * @param idNumber 证件号码
 * @returns 脱敏后的证件号码
 */
export function maskIdNumber(idNumber: string | null | undefined): string {
  if (!idNumber || idNumber.length < 8) {
    return idNumber || '';
  }
  const start = idNumber.slice(0, 4);
  const end = idNumber.slice(-4);
  return `${start}****${end}`;
}

/**
 * 证件有效性状态
 */
export interface ValidityStatus {
  text: string;
  status: 'success' | 'default' | 'processing';
}

/**
 * 获取证件有效性状态
 * @param expiryDate 失效日期 (格式: YYYY-MM-DD)
 * @returns 有效性状态对象
 */
export function getValidityStatus(
  expiryDate: string | null | undefined,
): ValidityStatus {
  if (!expiryDate) {
    return { text: '未知', status: 'default' };
  }

  // 长期有效
  if (expiryDate === '9999-12-31') {
    return { text: '长期有效', status: 'processing' };
  }

  // 判断是否有效
  const isValid = dayjs().isBefore(dayjs(expiryDate), 'day');
  return isValid
    ? { text: '有效', status: 'success' }
    : { text: '已失效', status: 'default' };
}
