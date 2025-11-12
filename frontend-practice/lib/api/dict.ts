import { httpClient } from "./request"

/**
 * 字典数据项
 */
export interface DictData {
  label: string // 字典标签
  value: string // 字典值
  dictType: string // 字典类型
  colorType?: string // 颜色类型
  cssClass?: string // CSS样式
}

/**
 * 获取指定字典类型的所有数据
 */
export async function getDictDataList(dictType: string): Promise<DictData[]> {
  const data = await httpClient.get<DictData[]>("/system/dict-data/simple-list")
  return data.filter((item) => item.dictType === dictType)
}

/**
 * 字典缓存
 */
const dictCache = new Map<string, DictData[]>()

/**
 * 获取字典数据(带缓存)
 */
export async function getDictData(dictType: string): Promise<DictData[]> {
  if (dictCache.has(dictType)) {
    return dictCache.get(dictType)!
  }

  const data = await getDictDataList(dictType)
  dictCache.set(dictType, data)
  return data
}

/**
 * 根据字典值获取字典标签
 */
export async function getDictLabel(dictType: string, value: string): Promise<string> {
  if (!value) return ""

  const dictList = await getDictData(dictType)
  const dict = dictList.find((item) => item.value === value)
  return dict?.label || value
}

/**
 * 批量获取字典标签
 */
export async function getDictLabels(
  dictType: string,
  values: string[],
): Promise<{ [key: string]: string }> {
  const dictList = await getDictData(dictType)
  const result: { [key: string]: string } = {}

  values.forEach((value) => {
    const dict = dictList.find((item) => item.value === value)
    result[value] = dict?.label || value
  })

  return result
}

/**
 * 清除字典缓存
 */
export function clearDictCache(dictType?: string) {
  if (dictType) {
    dictCache.delete(dictType)
  } else {
    dictCache.clear()
  }
}
