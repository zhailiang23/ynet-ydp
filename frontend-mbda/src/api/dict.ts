/**
 * 字典数据 API
 */
import request from '@/utils/request'

/** 字典数据项 */
export interface DictDataItem {
  label: string
  value: string
  colorType?: string
  cssClass?: string
}

/**
 * 获取字典数据列表
 */
export function getDictDataList(dictType: string): Promise<DictDataItem[]> {
  return request({
    url: '/admin-api/system/dict-data/simple-list',
    method: 'get',
    params: { dictType },
  })
}

/**
 * 根据字典类型和值获取标签
 */
export async function getDictLabel(dictType: string, value: string): Promise<string> {
  try {
    const list = await getDictDataList(dictType)
    const item = list.find(item => item.value === value)
    return item?.label || value
  } catch (error) {
    console.error(`获取字典标签失败: ${dictType}.${value}`, error)
    return value
  }
}
