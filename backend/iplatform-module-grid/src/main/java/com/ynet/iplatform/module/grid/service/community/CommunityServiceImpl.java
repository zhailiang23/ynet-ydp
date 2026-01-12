package com.ynet.iplatform.module.grid.service.community;

import com.ynet.iplatform.framework.common.pojo.PageResult;
import com.ynet.iplatform.framework.common.util.object.BeanUtils;
import com.ynet.iplatform.module.grid.controller.admin.community.vo.CommunityPageReqVO;
import com.ynet.iplatform.module.grid.controller.admin.community.vo.CommunityRespVO;
import com.ynet.iplatform.module.grid.controller.admin.community.vo.CommunitySaveReqVO;
import com.ynet.iplatform.module.grid.dal.dataobject.community.CommunityInfoDO;
import com.ynet.iplatform.module.grid.dal.mysql.community.CommunityMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import static com.ynet.iplatform.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.ynet.iplatform.module.grid.enums.ErrorCodeConstants.COMMUNITY_NOT_EXISTS;

/**
 * 社区信息 Service 实现类
 *
 * @author 易诚源码
 */
@Service
@Validated
public class CommunityServiceImpl implements CommunityService {

    @Resource
    private CommunityMapper communityMapper;

    @Override
    public Long createCommunity(CommunitySaveReqVO createReqVO) {
        // 自动生成社区编号
        String communityCode = "COMM_" + System.currentTimeMillis();

        // 插入
        CommunityInfoDO community = BeanUtils.toBean(createReqVO, CommunityInfoDO.class);
        community.setCommunityCode(communityCode);
        communityMapper.insert(community);

        // 返回
        return community.getId();
    }

    @Override
    public void updateCommunity(CommunitySaveReqVO updateReqVO) {
        // 校验存在
        validateCommunityExists(updateReqVO.getId());

        // 更新
        CommunityInfoDO updateObj = BeanUtils.toBean(updateReqVO, CommunityInfoDO.class);
        communityMapper.updateById(updateObj);
    }

    @Override
    public void deleteCommunity(Long id) {
        // 校验存在
        validateCommunityExists(id);

        // 删除
        communityMapper.deleteById(id);
    }

    private void validateCommunityExists(Long id) {
        if (communityMapper.selectById(id) == null) {
            throw exception(COMMUNITY_NOT_EXISTS);
        }
    }

    @Override
    public CommunityRespVO getCommunity(Long id) {
        CommunityInfoDO community = communityMapper.selectById(id);
        return BeanUtils.toBean(community, CommunityRespVO.class);
    }

    @Override
    public PageResult<CommunityRespVO> getCommunityPage(CommunityPageReqVO pageReqVO) {
        PageResult<CommunityInfoDO> pageResult = communityMapper.selectPage(pageReqVO);
        return BeanUtils.toBean(pageResult, CommunityRespVO.class);
    }

}
