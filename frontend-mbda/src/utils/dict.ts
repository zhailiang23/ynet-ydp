/**
 * 字典工具函数
 */
import { getDictDataList, type DictDataItem } from '@/api/dict'

// 字典缓存
const dictCache = new Map<string, DictDataItem[]>()

/**
 * 获取字典数据（带缓存）
 */
export async function getDictData(dictType: string): Promise<DictDataItem[]> {
  // 从缓存中获取
  if (dictCache.has(dictType)) {
    return dictCache.get(dictType)!
  }

  // 从接口获取
  try {
    const data = await getDictDataList(dictType)
    dictCache.set(dictType, data)
    return data
  } catch (error) {
    console.error(`获取字典数据失败: ${dictType}`, error)
    return []
  }
}

/**
 * 根据字典值获取标签
 */
export async function getDictLabel(dictType: string, value: string): Promise<string> {
  const data = await getDictData(dictType)
  const item = data.find(item => item.value === value)
  return item?.label || value
}

/**
 * 清空字典缓存
 */
export function clearDictCache() {
  dictCache.clear()
}
