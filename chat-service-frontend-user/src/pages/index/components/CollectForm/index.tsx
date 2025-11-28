import React from 'react'
import { View, Text, Input, Picker, Button, ScrollView } from '@tarojs/components'
import Taro from '@tarojs/taro'
import { submitCollectInfo } from '../../../../api'

// 年龄段选项
const ageOptions = [
  '18-25岁',
  '26-35岁',
  '36-45岁',
  '46-55岁',
  '55岁以上'
]

// 年收入选项
const incomeOptions = [
  '10万以下',
  '10-30万',
  '30-50万',
  '50-100万',
  '100万以上'
]

interface FormData {
  姓名: string
  手机号: string
  邮箱: string
  年龄: string
  职业: string
  年收入: string
}

interface CollectFormProps {
  onClose: () => void
  onSubmit?: (data: FormData) => void
  templateId?: number
  adminId?: number
}

const CollectForm: React.FC<CollectFormProps> = ({ onClose, onSubmit, templateId, adminId }) => {
  const [formData, setFormData] = React.useState<FormData>({
    姓名: '',
    手机号: '',
    邮箱: '',
    年龄: '',
    职业: '',
    年收入: ''
  })

  const [ageIndex, setAgeIndex] = React.useState<number>(-1)
  const [incomeIndex, setIncomeIndex] = React.useState<number>(-1)

  const handleInputChange = (field: keyof FormData, value: string) => {
    setFormData(prev => ({
      ...prev,
      [field]: value
    }))
  }

  const handleAgeChange = (e: any) => {
    const index = parseInt(e.detail.value, 10)
    setAgeIndex(index)
    setFormData(prev => ({
      ...prev,
      年龄: ageOptions[index]
    }))
  }

  const handleIncomeChange = (e: any) => {
    const index = parseInt(e.detail.value, 10)
    setIncomeIndex(index)
    setFormData(prev => ({
      ...prev,
      年收入: incomeOptions[index]
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
        Taro.showToast({ title: '提交成功', icon: 'success' })
        // 提交成功后关闭表单
        setTimeout(() => {
          onClose()
        }, 1500)
      } else {
        Taro.showToast({ title: '提交失败，请重试', icon: 'none' })
      }
    } catch (error) {
      console.error('提交留资信息失败:', error)
      Taro.showToast({ title: '提交失败，请重试', icon: 'none' })
    } finally {
      setSubmitting(false)
    }
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
          填写信息
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
              填写基本信息
            </Text>
            <Text
              style={{
                fontSize: '13px',
                color: '#999999',
                marginTop: '6px',
                display: 'block'
              }}
            >
              为您提供更精准的理财建议
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

          {/* 邮箱 */}
          <View style={{ marginBottom: '16px' }}>
            <View style={{ marginBottom: '6px' }}>
              <Text style={{ fontSize: '14px', color: '#333333', fontWeight: '500' }}>邮箱</Text>
            </View>
            <Input
              placeholder="请输入邮箱地址"
              placeholderStyle="color: #cccccc"
              value={formData.邮箱}
              onInput={(e) => handleInputChange('邮箱', e.detail.value)}
              style={{
                height: '44px',
                backgroundColor: '#f8f8f8',
                borderRadius: '8px',
                padding: '0 12px',
                fontSize: '14px'
              }}
            />
          </View>

          {/* 年龄 */}
          <View style={{ marginBottom: '16px' }}>
            <View style={{ marginBottom: '6px' }}>
              <Text style={{ fontSize: '14px', color: '#333333', fontWeight: '500' }}>年龄</Text>
            </View>
            <Picker mode="selector" range={ageOptions} onChange={handleAgeChange}>
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
                <Text style={{ color: ageIndex >= 0 ? '#333333' : '#cccccc', fontSize: '14px' }}>
                  {ageIndex >= 0 ? ageOptions[ageIndex] : '请选择年龄段'}
                </Text>
                <Text style={{ color: '#cccccc', fontSize: '12px' }}>▼</Text>
              </View>
            </Picker>
          </View>

          {/* 职业 */}
          <View style={{ marginBottom: '16px' }}>
            <View style={{ marginBottom: '6px' }}>
              <Text style={{ fontSize: '14px', color: '#333333', fontWeight: '500' }}>职业</Text>
            </View>
            <Input
              placeholder="请输入您的职业"
              placeholderStyle="color: #cccccc"
              value={formData.职业}
              onInput={(e) => handleInputChange('职业', e.detail.value)}
              style={{
                height: '44px',
                backgroundColor: '#f8f8f8',
                borderRadius: '8px',
                padding: '0 12px',
                fontSize: '14px'
              }}
            />
          </View>

          {/* 年收入 */}
          <View style={{ marginBottom: '20px' }}>
            <View style={{ marginBottom: '6px' }}>
              <Text style={{ fontSize: '14px', color: '#333333', fontWeight: '500' }}>年收入</Text>
            </View>
            <Picker mode="selector" range={incomeOptions} onChange={handleIncomeChange}>
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
                <Text style={{ color: incomeIndex >= 0 ? '#333333' : '#cccccc', fontSize: '14px' }}>
                  {incomeIndex >= 0 ? incomeOptions[incomeIndex] : '请选择年收入范围'}
                </Text>
                <Text style={{ color: '#cccccc', fontSize: '12px' }}>▼</Text>
              </View>
            </Picker>
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
            {submitting ? '提交中...' : '提交'}
          </Button>
        </View>
      </ScrollView>
    </View>
  )
}

export default CollectForm
