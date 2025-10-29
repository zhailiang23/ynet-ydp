# Proposal: Add Retail Customer Family Information Tab

## Why

The retail customer detail page needs to display comprehensive family information including:
- Family basic information (family size, dependents, household information)
- Family financial status (income, assets, liabilities)
- Family member details (name, relation, education, occupation)

Currently, while the backend data models exist (CustomerFamilyDO and CustomerFamilyMemberDO with full CRUD APIs), the frontend lacks proper UI implementation due to character encoding issues that need to be resolved for proper Chinese text display.

## What Changes

- Add family information card component to display household data
- Add family members list with dual view modes (table and card views)
- Integrate family info tab into retail customer detail page
- Fix character encoding issues in Vue component files
- Implement view mode persistence using localStorage
- Add proper dictionary mappings for family-related enumerations

## Impact

- **Affected specs**: retail-customer-family (new capability)
- **Affected code**:
  - Backend: `ynet-module-crm/src/main/java/cn/iocoder/yudao/module/aicrm/` (already implemented)
    - `controller/admin/customerfamily/CustomerFamilyController.java`
    - `controller/admin/customerfamilymember/CustomerFamilyMemberController.java`
    - `service/customerfamily/CustomerFamilyServiceImpl.java`
    - `service/customerfamilymember/CustomerFamilyMemberServiceImpl.java`
    - `dal/dataobject/customerfamily/CustomerFamilyDO.java`
    - `dal/dataobject/customerfamilymember/CustomerFamilyMemberDO.java`
  - Frontend: `frontend/apps/web-antd/src/views/aicrm/retail-customer/`
    - `components/FamilyInfoCard.vue` (needs encoding fix)
    - `components/FamilyMemberCardView.vue` (completed)
    - `pages/family-info.vue` (needs encoding fix)
    - `detail.vue` (route integration completed)
  - API: `frontend/apps/web-antd/src/api/aicrm/`
    - `customerfamily/index.ts` (completed)
    - `customerfamilymember/index.ts` (completed)

- **Breaking changes**: None
- **Dependencies**: Requires dictionary data for family-related enumerations (aicrm_family_* types)
