<script lang="ts" setup>
import type { GridZerodaiCustomerApi } from '#/api/grid/zerodaicustomer';
import type { UploadProps } from 'ant-design-vue';

import { computed, reactive, ref, watch } from 'vue';

import { useVbenModal } from '@vben/common-ui';
import { message, Upload } from 'ant-design-vue';

import { createZerodaiCustomer, getZerodaiCustomer, updateZerodaiCustomer } from '#/api/grid/zerodaicustomer';
import { $t } from '#/locales';
import { useUserStore } from '@vben/stores';
import { useUpload } from '#/components/upload/use-upload';

import CustomerMapPoint from './customer-map-point.vue';

const emit = defineEmits(['success']);
const formData = ref<GridZerodaiCustomerApi.ZerodaiCustomer>();
const userStore = useUserStore();
const { httpRequest } = useUpload();

const getTitle = computed(() => {
  return formData.value?.id
    ? $t('ui.actionTitle.edit', ['零贷客户'])
    : $t('ui.actionTitle.create', ['零贷客户']);
});

// 图片上传相关
const fileList = ref<UploadProps['fileList']>([]);
const uploading = ref(false);

// 上传前校验
const beforeUpload = (file: File) => {
  const isImage = file.type.startsWith('image/');
  if (!isImage) {
    message.error('只能上传图片文件！');
    return false;
  }
  const isLt5M = file.size / 1024 / 1024 < 5;
  if (!isLt5M) {
    message.error('图片大小不能超过 5MB！');
    return false;
  }
  return true;
};

// 自定义上传
const customRequest = async (options: any) => {
  const { file, onSuccess, onError } = options;
  uploading.value = true;
  try {
    const result = await httpRequest(file);

    // 验证上传结果是否包含有效的 URL
    if (!result || !result.url || typeof result.url !== 'string' || result.url.trim() === '') {
      console.error('上传返回的 URL 无效:', result);
      throw new Error('上传返回的 URL 无效');
    }

    onSuccess(result);
    message.success('上传成功');
    // 将 URL 添加到表单数据
    const photoUrls = formState.photos ? JSON.parse(formState.photos) : [];
    photoUrls.push(result.url);
    // 过滤掉可能存在的 null 值，确保数组中只有有效的 URL
    const validUrls = photoUrls.filter((url: any) => url && typeof url === 'string' && url.trim() !== '');
    formState.photos = JSON.stringify(validUrls);
  } catch (error) {
    console.error('上传失败:', error);
    onError(error);
    message.error('上传失败');
  } finally {
    uploading.value = false;
  }
};

// 删除图片
const handleRemove = (file: any) => {
  const photoUrls = formState.photos ? JSON.parse(formState.photos) : [];
  const index = photoUrls.indexOf(file.url || file.response?.url);
  if (index > -1) {
    photoUrls.splice(index, 1);
    // 过滤掉可能存在的 null 值，确保数组中只有有效的 URL
    const validUrls = photoUrls.filter((url: any) => url && typeof url === 'string' && url.trim() !== '');
    formState.photos = validUrls.length > 0 ? JSON.stringify(validUrls) : null;
  }
};

// 表单数据
const formState = reactive<any>({
  id: undefined,
  customerName: '',
  address: '',
  longitude: undefined,
  latitude: undefined,
  principalName: '',
  gender: '男',
  phone: '',
  businessSituation: '',
  financingSituation: '',
  creditDemand: '',
  customerNo: '',
  customerClassification: '潜力客户',
  creditAmount: undefined,
  loanAmount: undefined,
  status: '营销中',
  photos: null,
  employeeNo: '',
  employeeName: '',
  managerId: undefined,
});

// 地图坐标数据
const coordinates = ref<{
  longitude: number | null;
  latitude: number | null;
  address?: string | null;
}>({
  longitude: null,
  latitude: null,
  address: null,
});

// 监听坐标变化，实时同步到 formState
watch(
  coordinates,
  (newVal) => {
    if (newVal) {
      formState.longitude = newVal.longitude ?? undefined;
      formState.latitude = newVal.latitude ?? undefined;
      formState.address = newVal.address ?? '';
    }
  },
  { deep: true }
);

// 初始化表单数据
async function initFormData(data: GridZerodaiCustomerApi.ZerodaiCustomer | null | undefined) {
  if (data?.id) {
    // 编辑模式
    try {
      const result = await getZerodaiCustomer(data.id);
      Object.assign(formState, result);

      // 初始化地图坐标
      coordinates.value = {
        longitude: result.longitude ?? null,
        latitude: result.latitude ?? null,
        address: result.address ?? null,
      };

      // 初始化图片列表
      if (result.photos) {
        try {
          const photoUrls = JSON.parse(result.photos);
          // 过滤掉 null、undefined 和空字符串
          const validUrls = photoUrls.filter((url: any) => url && typeof url === 'string' && url.trim() !== '');
          fileList.value = validUrls.map((url: string, index: number) => ({
            uid: `-${index}`,
            name: `图片${index + 1}`,
            status: 'done',
            url: url,
          }));
        } catch (e) {
          console.error('解析照片数据失败:', e);
          fileList.value = [];
        }
      } else {
        fileList.value = [];
      }
    } catch (error) {
      console.error('加载数据失败:', error);
      message.error('加载数据失败');
    }
  } else {
    // 新建模式：重置并填充当前用户信息
    formState.id = undefined;
    formState.employeeNo = userStore.userInfo?.username || '';
    formState.employeeName = userStore.userInfo?.nickname || '';
    formState.managerId = userStore.userInfo?.id || null;
    formState.customerName = '';
    formState.address = '';
    formState.longitude = undefined;
    formState.latitude = undefined;
    formState.principalName = '';
    formState.gender = '男';
    formState.phone = '';
    formState.businessSituation = '';
    formState.financingSituation = '';
    formState.creditDemand = '';
    // Mock 客户号（格式：CUS + 当前日期 + 随机4位数）
    const today = new Date().toISOString().slice(0, 10).replace(/-/g, '');
    const randomNum = Math.floor(Math.random() * 10000).toString().padStart(4, '0');
    formState.customerNo = `CUS${today}${randomNum}`;
    formState.customerClassification = '潜力客户';
    // Mock 授信金额和在贷金额
    formState.creditAmount = 50.00;
    formState.loanAmount = 30.00;
    formState.status = '营销中';
    formState.photos = null;

    // 重置地图坐标
    coordinates.value = {
      longitude: null,
      latitude: null,
      address: null,
    };

    // 重置图片列表
    fileList.value = [];
  }
}

// 提交表单
async function handleSubmit() {
  // 验证必填字段
  if (!formState.customerName) {
    message.error('请输入商户名称');
    return false;
  }
  if (!formState.principalName) {
    message.error('请输入负责人姓名');
    return false;
  }
  if (!formState.gender) {
    message.error('请选择性别');
    return false;
  }
  if (!formState.phone) {
    message.error('请输入手机号');
    return false;
  }
  // 验证手机号格式
  if (!/^1[3-9]\d{9}$/.test(formState.phone)) {
    message.error('请输入正确的手机号');
    return false;
  }

  // 同步坐标数据到 formState
  formState.longitude = coordinates.value.longitude ?? undefined;
  formState.latitude = coordinates.value.latitude ?? undefined;
  formState.address = coordinates.value.address ?? formState.address;

  // 确保 photos 字段不是空字符串（JSON 字段不允许空字符串）
  // 同时过滤掉数组中的 null 值
  if (formState.photos === '') {
    formState.photos = null;
  } else if (formState.photos) {
    try {
      const photoUrls = JSON.parse(formState.photos);
      // 过滤掉 null、undefined 和空字符串
      const validUrls = photoUrls.filter((url: any) => url && typeof url === 'string' && url.trim() !== '');
      formState.photos = validUrls.length > 0 ? JSON.stringify(validUrls) : null;
    } catch (e) {
      console.error('解析 photos 失败，将设置为 null:', e);
      formState.photos = null;
    }
  }

  console.log('=== 提交零贷客户数据 ===');
  console.log('coordinates:', coordinates.value);
  console.log('formState:', JSON.stringify(formState, null, 2));

  try {
    if (formState.id) {
      await updateZerodaiCustomer(formState);
    } else {
      await createZerodaiCustomer(formState);
    }
    return true;
  } catch (error) {
    console.error('提交失败:', error);
    return false;
  }
}

const [Modal, modalApi] = useVbenModal({
  async onConfirm() {
    const success = await handleSubmit();
    if (!success) {
      return;
    }
    await modalApi.close();
    emit('success');
    message.success($t('ui.actionMessage.operationSuccess'));
  },
  async onOpenChange(isOpen: boolean) {
    if (!isOpen) {
      formData.value = undefined;
      return;
    }
    const data = modalApi.getData<GridZerodaiCustomerApi.ZerodaiCustomer>();
    formData.value = data;
    modalApi.lock();
    try {
      await initFormData(data);
    } finally {
      modalApi.unlock();
    }
  },
});
</script>

<template>
  <Modal
    :title="getTitle"
    :footer-props="{ okText: '确定', cancelText: '取消' }"
    class="!w-[90vw]"
  >
    <div class="flex h-[700px] gap-4">
      <!-- 左侧地图区域 (2/3) -->
      <div class="w-2/3">
        <CustomerMapPoint v-model="coordinates" />
      </div>

      <!-- 右侧表单区域 (1/3) -->
      <div class="w-1/3 overflow-y-auto">
        <a-form layout="vertical" :model="formState">
          <a-form-item label="商户名称" name="customerName">
            <a-input v-model:value="formState.customerName" placeholder="请输入商户名称" />
          </a-form-item>

          <a-form-item label="经营地址">
            <a-textarea
              v-model:value="formState.address"
              :rows="3"
              placeholder="点击地图选择位置后自动填充，也可手动输入"
            />
          </a-form-item>

          <a-form-item label="负责人姓名" name="principalName">
            <a-input v-model:value="formState.principalName" placeholder="请输入负责人姓名" />
          </a-form-item>

          <a-form-item label="性别" name="gender">
            <a-radio-group v-model:value="formState.gender">
              <a-radio value="男">男</a-radio>
              <a-radio value="女">女</a-radio>
            </a-radio-group>
          </a-form-item>

          <a-form-item label="手机号" name="phone">
            <a-input v-model:value="formState.phone" placeholder="请输入手机号" />
          </a-form-item>

          <a-form-item label="经营情况">
            <a-textarea
              v-model:value="formState.businessSituation"
              :rows="3"
              placeholder="请输入近1年营业收入、实际经营年限、净利润、家庭资产等"
            />
          </a-form-item>

          <a-form-item label="当前融资情况">
            <a-textarea
              v-model:value="formState.financingSituation"
              :rows="3"
              placeholder="请输入当前融资情况"
            />
          </a-form-item>

          <a-form-item label="对我行信贷等产品需求">
            <a-textarea
              v-model:value="formState.creditDemand"
              :rows="3"
              placeholder="请输入对我行信贷等产品需求"
            />
          </a-form-item>

          <a-form-item label="客户号">
            <a-input v-model:value="formState.customerNo" disabled />
          </a-form-item>

          <a-form-item label="客户分类">
            <a-select v-model:value="formState.customerClassification">
              <a-select-option value="潜力客户">潜力客户</a-select-option>
              <a-select-option value="月标客户">月标客户</a-select-option>
              <a-select-option value="存量客户">存量客户</a-select-option>
            </a-select>
          </a-form-item>

          <a-form-item label="授信金额（万元）">
            <a-input-number
              v-model:value="formState.creditAmount"
              :min="0"
              :precision="2"
              disabled
              class="w-full"
            />
          </a-form-item>

          <a-form-item label="在贷金额（万元）">
            <a-input-number
              v-model:value="formState.loanAmount"
              :min="0"
              :precision="2"
              disabled
              class="w-full"
            />
          </a-form-item>

          <a-form-item label="状态">
            <a-select v-model:value="formState.status">
              <a-select-option value="营销中">营销中</a-select-option>
              <a-select-option value="二次营销">二次营销</a-select-option>
              <a-select-option value="待分支行审批">待分支行审批</a-select-option>
              <a-select-option value="待总行审批">待总行审批</a-select-option>
              <a-select-option value="已审批">已审批</a-select-option>
              <a-select-option value="被拒">被拒</a-select-option>
            </a-select>
          </a-form-item>

          <a-form-item label="上传照片">
            <Upload
              v-model:file-list="fileList"
              :custom-request="customRequest"
              :before-upload="beforeUpload"
              :remove="handleRemove"
              list-type="picture-card"
              accept="image/*"
              :max-count="9"
            >
              <div v-if="fileList && fileList.length < 9">
                <span class="icon-[ant-design--plus-outlined]"></span>
                <div style="margin-top: 8px">上传</div>
              </div>
            </Upload>
            <div class="text-xs text-gray-500 mt-1">
              支持上传图片，单个文件不超过 5MB，最多 9 张
            </div>
          </a-form-item>

          <a-form-item label="员工号">
            <a-input v-model:value="formState.employeeNo" placeholder="当前用户" disabled />
          </a-form-item>

          <a-form-item label="员工姓名">
            <a-input v-model:value="formState.employeeName" placeholder="当前用户" disabled />
          </a-form-item>
        </a-form>
      </div>
    </div>
  </Modal>
</template>
