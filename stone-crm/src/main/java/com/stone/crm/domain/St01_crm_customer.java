package com.stone.crm.domain;

import com.stone.common.annotation.DictValue;
import com.stone.common.annotation.Excel;
import com.stone.common.core.domain.BaseEntity;
import com.stone.common.utils.equator.EqualsAnnotation;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

/**
 * 客户对象 st01_crm_customer
 * 
 * @author stone
 * @date 2024-04-25
 */
@Data
public class St01_crm_customer extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 客户号 */
    @ApiModelProperty(value = "客户号", example = "c-0000000001")
    private String customerId;

    @ApiModelProperty(value = "年龄", example = "18")
    @Excel(name = "年龄")
    private int age;

    @ApiModelProperty(value = "性别", example = "1", notes = "1：男；2：女")
    @Excel(name = "性别")
    private int sex;



    /** 客户名称 */
    @ApiModelProperty(value = "客户名称", example = "张三")
    @Excel(name = "客户名称")
    @EqualsAnnotation("客户名称")
    private String customerName;

    /** 客户性质;1：企业客户
2：个人客户
3：政府单位
4：事业单位 */
    @Excel(name = "客户性质;1：企业客户2：个人客户3：政府单位4：事业单位")
    @DictValue(disp="natureDisp",dict="customer_nature")
    private String nature;

    @EqualsAnnotation("客户性质")
    private String natureDisp;
    /** 客户来源;1：电话咨询
2：自主开拓
3：公司资源
4：网络推广
5：渠道代理
6：其他方式 */
    @Excel(name = "客户来源;1：电话咨询 2：自主开拓 3：公司资源 4：网络推广 5：渠道代理 6：其他方式")
    @DictValue(disp="sourceDisp",dict="customer_source")
    private String source;
    @EqualsAnnotation("客户来源")
    private String sourceDisp;
    /** 客户行业;1：金融
2：电信
3：教育
4：高科技
5：政府
6：制造业
7：服务业
8：能源
9：零售
10：媒体
11：娱乐
12：咨询
13：非盈利事业
14：公用事业
15：其他 */
    @Excel(name = "客户行业;1：金融 2：电信 3：教育 4：高科技 5：政府 6：制造业 7：服务业 8：能源 9：零售 10：媒体 11：娱乐 12：咨询 13：非盈利事业 14：公用事业 15：其他")
    @DictValue(disp = "industryDisp",dict="customer_industry")
    private String industry;
    @EqualsAnnotation("客户行业")
    private String industryDisp;
    /** 客户分级;1：大型
2：中型
3：小型
4：微型 */
    @Excel(name = "客户分级;1：大型 2：中型 3：小型 4：微型")
    @DictValue(disp = "gradeDisp" , dict = "customer_grade")
    private String grade;
    @EqualsAnnotation("客户分级")
    private String gradeDisp;
    /** 客户阶段;1：初步接洽
2：确认需求
3：解决方案
4：商务谈判
5：成交
6：流失 */
    @Excel(name = "客户阶段;1：初步接洽 2：确认需求 3：解决方案 4：商务谈判 5：成交 6：流失")
    @DictValue(disp = "stageDisp",dict = "customer_stage")
    private String stage;
    @EqualsAnnotation("客户阶段")
    private String stageDisp;
    /** 客户官网 */
    @Excel(name = "客户官网")
    private String website;

    /** 客户类型;1：最终客户
2：渠道客户
3：竞争对手 */
    @Excel(name = "客户类型;1：最终客户 2：渠道客户 3：竞争对手")
    @DictValue(disp = "typeDisp",dict = "customer_type")
    private String type;
    private String typeDisp;
    /** 客户简介 */
    @Excel(name = "客户简介")
    @EqualsAnnotation("客户简介")
    private String introduction;

    /** 重要程度 */
    @Excel(name = "重要程度")
    private String importance;

    /** 锁定状态;0：解锁1：锁定 */
    @Excel(name = "锁定状态;0：解锁1：锁定")
    private String lockStatus;

    /** 详细地址 */
    @Excel(name = "详细地址")
    @EqualsAnnotation("详细地址")
    @ApiModelProperty("详细地址")
    private String address;

    /** 省 */
    @Excel(name = "省")
    private String provinceId;

    /** 市 */
    @Excel(name = "市")
    private String cityId;

    /** 区 */
    @Excel(name = "区")
    private String areaId;

    /** 销售负责人 */
    @Excel(name = "销售负责人")
    private String ownerUserId;

    /** 售后负责人 */
    @Excel(name = "售后负责人")
    private String saUserId;

    /** 删除标记;0：未删除1：已删除 */
    @Excel(name = "删除标记;0：未删除1：已删除")
    private String deltag;

    @Excel(name="下次联系时间")
    private String nextContactTime;

    /** 创建人 */
    @Excel(name = "创建人")
    private String createdBy;

    /** 创建时间 */
    @Excel(name = "创建时间")
    private String createdTime;

    /** 更新人 */
    @Excel(name = "更新人")
    private String updatedBy;

    /** 更新时间 */
    @Excel(name = "更新时间")
    private String updatedTime;

    public static void main(String[] args) throws IntrospectionException, InvocationTargetException, IllegalAccessException {
        St01_crm_customer st=new St01_crm_customer();
        st.setNature("123456");
        Field[] field= St01_crm_customer.class.getDeclaredFields();
        for (Field f : field) {
            Annotation[] anno=f.getAnnotations();
            for (Annotation a : anno) {
                if(a instanceof DictValue){
                    String disp=((DictValue) a).disp();
                    String dict=((DictValue) a).dict();
                    PropertyDescriptor pd=new PropertyDescriptor(f.getName(), St01_crm_customer.class);
                    PropertyDescriptor pd1=new PropertyDescriptor(disp, St01_crm_customer.class);
                    System.out.println(pd.getReadMethod().invoke(st));
                    pd1.getWriteMethod().invoke(st,"123");
                    System.out.println(st.getNatureDisp());
                }
            }
        }



    }
}
