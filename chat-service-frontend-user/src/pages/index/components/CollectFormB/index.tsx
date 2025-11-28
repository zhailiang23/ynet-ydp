import React from 'react'
import { View, Text, Input, Picker, Button, ScrollView, Textarea } from '@tarojs/components'
import Taro from '@tarojs/taro'
import { submitCollectInfo } from '../../../../api'

// 咨询类型选项
const consultTypeOptions = [
  '理财咨询',
  '贷款咨询',
  '保险咨询',
  '基金咨询',
  '其他咨询'
]

// 预约时间段选项
const timeSlotOptions = [
  '上午 9:00-12:00',
  '下午 14:00-18:00',
  '晚上 19:00-21:00'
]

interface FormData {
  姓名: string
  手机号: string
  咨询类型: string
  预约日期: string
  预约时段: string
  咨询内容: string
}

interface CollectFormBProps {
  onClose: () => void
  onSubmit?: (data: FormData) => void
  templateId?: number
  adminId?: number
}

const CollectFormB: React.FC<CollectFormBProps> = ({ onClose, onSubmit, templateId, adminId }) => {
  const [formData, setFormData] = React.useState<FormData>({
    姓名: '',
    手机号: '',
    咨询类型: '',
    预约日期: '',
    预约时段: '',
    咨询内容: ''
  })

  const [consultTypeIndex, setConsultTypeIndex] = React.useState<number>(-1)
  const [timeSlotIndex, setTimeSlotIndex] = React.useState<number>(-1)

  const handleInputChange = (field: keyof FormData, value: string) => {
    setFormData(prev => ({
      ...prev,
      [field]: value
    }))
  }

  const handleConsultTypeChange = (e: any) => {
    const index = parseInt(e.detail.value, 10)
    setConsultTypeIndex(index)
    setFormData(prev => ({
      ...prev,
      咨询类型: consultTypeOptions[index]
    }))
  }

  const handleDateChange = (e: any) => {
    setFormData(prev => ({
      ...prev,
      预约日期: e.detail.value
    }))
  }

  const handleTimeSlotChange = (e: any) => {
    const index = parseInt(e.detail.value, 10)
    setTimeSlotIndex(index)
    setFormData(prev => ({
      ...prev,
      预约时段: timeSlotOptions[index]
    }))
  }

  const [submitting, setSubmitting] = React.useState(false)

  const handleSubmit = async () => {
    // 验证必填字段
    if (!formData.姓名.trim()) {
      Taro.showToast({ title: '请输入姓名', icon: 'none' })
      return
    }
    if (!formData.手机号.trim()) {
      Taro.showToast({ title: '请输入手机号', icon: 'none' })
      return
    }
    // 验证手机号格式
    const phoneReg = /^1[3-9]\d{9}$/
    if (!phoneReg.test(formData.手机号)) {
      Taro.showToast({ title: '请输入正确的手机号', icon: 'none' })
      return
    }
    if (!formData.咨询类型) {
      Taro.showToast({ title: '请选择咨询类型', icon: 'none' })
      return
    }
    if (!formData.预约日期) {
      Taro.showToast({ title: '请选择预约日期', icon: 'none' })
      return
    }
    if (!formData.预约时段) {
      Taro.showToast({ title: '请选择预约时段', icon: 'none' })
      return
    }

    // 防止重复提交
    if (submitting) return
    setSubmitting(true)

    try {
      // 调用后端 API 提交留资信息，带上 templateId 和 adminId
      const result = await submitCollectInfo(formData, templateId, adminId)
      if (result && result.data && result.data.id) {
        // 调用提交回调
        if (onSubmit) {
          onSubmit(formData)
        }
        Taro.showToast({ title: '预约成功', icon: 'success' })
        // 提交成功后关闭表单
        setTimeout(() => {
          onClose()
        }, 1500)
      } else {
        Taro.showToast({ title: '预约失败，请重试', icon: 'none' })
      }
    } catch (error) {
      console.error('提交预约信息失败:', error)
      Taro.showToast({ title: '预约失败，请重试', icon: 'none' })
    } finally {
      setSubmitting(false)
    }
  }

  // 获取最小日期（今天）
  const getMinDate = () => {
    const today = new Date()
    const year = today.getFullYear()
    const month = String(today.getMonth() + 1).padStart(2, '0')
    const day = String(today.getDate()).padStart(2, '0')
    return `${year}-${month}-${day}`
  }

  // 获取最大日期（30天后）
  const getMaxDate = () => {
    const today = new Date()
    today.setDate(today.getDate() + 30)
    const year = today.getFullYear()
    const month = String(today.getMonth() + 1).padStart(2, '0')
    const day = String(today.getDate()).padStart(2, '0')
    return `${year}-${month}-${day}`
  }

  return (
    <View
      style={{
        position: 'absolute',
        top: 0,
        left: 0,
        right: 0,
        bottom: 0,
        backgroundColor: '#f5f5f5',
        zIndex: 100,
        display: 'flex',
        flexDirection: 'column'
      }}
    >
      {/* 顶部返回栏 */}
      <View
        style={{
          height: '44px',
          backgroundColor: '#ffffff',
          display: 'flex',
          alignItems: 'center',
          padding: '0 12px',
          borderBottom: '1px solid #e5e5e5'
        }}
      >
        <View
          onClick={onClose}
          style={{
            fontSize: '14px',
            color: '#1890ff',
            cursor: 'pointer'
          }}
        >
          返回
        </View>
        <View
          style={{
            flex: 1,
            textAlign: 'center',
            fontSize: '16px',
            fontWeight: '500',
            marginRight: '32px'
          }}
        >
          预约咨询
        </View>
      </View>

      {/* 表单内容区域 */}
      <ScrollView
        scrollY
        style={{
          flex: 1,
          overflow: 'auto'
        }}
      >
        {/* 表单卡片 */}
        <View
          style={{
            margin: '12px',
            backgroundColor: '#ffffff',
            borderRadius: '12px',
            padding: '20px 16px'
          }}
        >
          {/* 标题 */}
          <View style={{ textAlign: 'center', marginBottom: '20px' }}>
            <Text
              style={{
                fontSize: '20px',
                fontWeight: 'bold',
                color: '#333333',
                display: 'block'
              }}
            >
              预约咨询服务
            </Text>
            <Text
              style={{
                fontSize: '13px',
                color: '#999999',
                marginTop: '6px',
                display: 'block'
              }}
            >
              填写信息后我们将尽快与您联系
            </Text>
          </View>

          {/* 姓名 */}
          <View style={{ marginBottom: '16px' }}>
            <View style={{ marginBottom: '6px' }}>
              <Text style={{ fontSize: '14px', color: '#333333', fontWeight: '500' }}>姓名</Text>
              <Text style={{ color: '#ff4d4f', marginLeft: '4px' }}>*</Text>
            </View>
            <Input
              placeholder="请输入您的姓名"
              placeholderStyle="color: #cccccc"
              value={formData.姓名}
              onInput={(e) => handleInputChange('姓名', e.detail.value)}
              style={{
                height: '44px',
                backgroundColor: '#f8f8f8',
                borderRadius: '8px',
                padding: '0 12px',
                fontSize: '14px'
              }}
            />
          </View>

          {/* 手机号 */}
          <View style={{ marginBottom: '16px' }}>
            <View style={{ marginBottom: '6px' }}>
              <Text style={{ fontSize: '14px', color: '#333333', fontWeight: '500' }}>手机号</Text>
              <Text style={{ color: '#ff4d4f', marginLeft: '4px' }}>*</Text>
            </View>
            <Input
              type="number"
              maxlength={11}
              placeholder="请输入手机号"
              placeholderStyle="color: #cccccc"
              value={formData.手机号}
              onInput={(e) => handleInputChange('手机号', e.detail.value)}
              style={{
                height: '44px',
                backgroundColor: '#f8f8f8',
                borderRadius: '8px',
                padding: '0 12px',
                fontSize: '14px'
              }}
            />
          </View>

          {/* 咨询类型 */}
          <View style={{ marginBottom: '16px' }}>
            <View style={{ marginBottom: '6px' }}>
              <Text style={{ fontSize: '14px', color: '#333333', fontWeight: '500' }}>咨询类型</Text>
              <Text style={{ color: '#ff4d4f', marginLeft: '4px' }}>*</Text>
            </View>
            <Picker mode="selector" range={consultTypeOptions} onChange={handleConsultTypeChange}>
              <View
                style={{
                  height: '44px',
                  backgroundColor: '#f8f8f8',
                  borderRadius: '8px',
                  padding: '0 12px',
                  fontSize: '14px',
                  display: 'flex',
                  alignItems: 'center',
                  justifyContent: 'space-between'
                }}
              >
                <Text style={{ color: consultTypeIndex >= 0 ? '#333333' : '#cccccc', fontSize: '14px' }}>
                  {consultTypeIndex >= 0 ? consultTypeOptions[consultTypeIndex] : '请选择咨询类型'}
                </Text>
                <Text style={{ color: '#cccccc', fontSize: '12px' }}>▼</Text>
              </View>
            </Picker>
          </View>

          {/* 预约日期 */}
          <View style={{ marginBottom: '16px' }}>
            <View style={{ marginBottom: '6px' }}>
              <Text style={{ fontSize: '14px', color: '#333333', fontWeight: '500' }}>预约日期</Text>
              <Text style={{ color: '#ff4d4f', marginLeft: '4px' }}>*</Text>
            </View>
            <Picker
              mode="date"
              value={formData.预约日期}
              start={getMinDate()}
              end={getMaxDate()}
              onChange={handleDateChange}
            >
              <View
                style={{
                  height: '44px',
                  backgroundColor: '#f8f8f8',
                  borderRadius: '8px',
                  padding: '0 12px',
                  fontSize: '14px',
                  display: 'flex',
                  alignItems: 'center',
                  justifyContent: 'space-between'
                }}
              >
                <Text style={{ color: formData.预约日期 ? '#333333' : '#cccccc', fontSize: '14px' }}>
                  {formData.预约日期 || '请选择预约日期'}
                </Text>
                <Text style={{ color: '#cccccc', fontSize: '12px' }}>▼</Text>
              </View>
            </Picker>
          </View>

          {/* 预约时段 */}
          <View style={{ marginBottom: '16px' }}>
            <View style={{ marginBottom: '6px' }}>
              <Text style={{ fontSize: '14px', color: '#333333', fontWeight: '500' }}>预约时段</Text>
              <Text style={{ color: '#ff4d4f', marginLeft: '4px' }}>*</Text>
            </View>
            <Picker mode="selector" range={timeSlotOptions} onChange={handleTimeSlotChange}>
              <View
                style={{
                  height: '44px',
                  backgroundColor: '#f8f8f8',
                  borderRadius: '8px',
                  padding: '0 12px',
                  fontSize: '14px',
                  display: 'flex',
                  alignItems: 'center',
                  justifyContent: 'space-between'
                }}
              >
                <Text style={{ color: timeSlotIndex >= 0 ? '#333333' : '#cccccc', fontSize: '14px' }}>
                  {timeSlotIndex >= 0 ? timeSlotOptions[timeSlotIndex] : '请选择预约时段'}
                </Text>
                <Text style={{ color: '#cccccc', fontSize: '12px' }}>▼</Text>
              </View>
            </Picker>
          </View>

          {/* 咨询内容 */}
          <View style={{ marginBottom: '20px' }}>
            <View style={{ marginBottom: '6px' }}>
              <Text style={{ fontSize: '14px', color: '#333333', fontWeight: '500' }}>咨询内容</Text>
            </View>
            <Textarea
              placeholder="请简要描述您想咨询的内容（选填）"
              placeholderStyle="color: #cccccc"
              value={formData.咨询内容}
              onInput={(e) => handleInputChange('咨询内容', e.detail.value)}
              maxlength={200}
              style={{
                width: '100%',
                height: '100px',
                backgroundColor: '#f8f8f8',
                borderRadius: '8px',
                padding: '12px',
                fontSize: '14px',
                boxSizing: 'border-box'
              }}
            />
          </View>

          {/* 提交按钮 */}
          <Button
            onClick={handleSubmit}
            disabled={submitting}
            style={{
              width: '100%',
              height: '44px',
              backgroundColor: submitting ? '#a0c4e8' : '#1890ff',
              color: '#ffffff',
              fontSize: '15px',
              fontWeight: '500',
              borderRadius: '8px',
              border: 'none',
              lineHeight: '44px'
            }}
          >
            {submitting ? '提交中...' : '提交预约'}
          </Button>
        </View>
      </ScrollView>
    </View>
  )
}

export default CollectFormB
