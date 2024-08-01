<template>
  <div class="detail-container">
    <st-sections title="基本信息" style="margin: 10px 10px 0 10px;">
      <el-descriptions style="margin: 10px 10px 0 10px;" :column="2" size="medium">
        <el-descriptions-item v-for="item in baseItems" :label="item.lable">
          <dict-tag v-if="item.dict" :options="dict.type[item.dict]" :value="customerForm[item.name]" />
          <span v-else>{{ customerForm[item.name] }}</span>
        </el-descriptions-item>
      </el-descriptions>
    </st-sections>
    <st-sections title="身份信息" style="margin: 10px 10px 0 10px;">
      <el-descriptions style="margin: 10px 10px 0 10px;" :column="2" size="medium">
        <el-descriptions-item label="职业身份">{{ customerForm['professional']===1?'是':customerForm['professional']===0?'否':'-' }}</el-descriptions-item>
        <template v-if="customerForm['professional']===1">
          <el-descriptions-item v-for="item in infoItems" :label="item.lable">
            <dict-tag v-if="item.dict" :options="dict.type[item.dict]" :value="customerForm[item.name]" />
            <span v-else>{{ customerForm[item.name] }}</span>
          </el-descriptions-item>
        </template>
      </el-descriptions>
    </st-sections>
    <st-sections title="房产信息" style="margin: 10px 10px 0 10px;">
      <el-descriptions style="margin: 10px 10px 0 10px;" :column="2" size="medium">
        <el-descriptions-item label="是否有房">{{ customerForm['propertyInfo']===1?'是':customerForm['propertyInfo']===0?'否':'-' }}</el-descriptions-item>
        <template v-if="customerForm['propertyInfo']===1">
          <el-descriptions-item v-for="item in propertyItems" :label="item.lable">
            <dict-tag v-if="item.dict" :options="dict.type[item.dict]" :value="customerForm[item.name]" />
            <span v-else>{{ customerForm[item.name] }}</span>
          </el-descriptions-item>
        </template>
      </el-descriptions>
    </st-sections>
    <st-sections title="车产信息" style="margin: 10px 10px 0 10px;">
      <el-descriptions style="margin: 10px 10px 0 10px;" :column="2" size="medium">
        <el-descriptions-item label="是否有车">{{ customerForm['carInfo']===1?'是':customerForm['carInfo']===0?'否':'-' }}</el-descriptions-item>
        <template v-if="customerForm['carInfo']===1">
          <el-descriptions-item v-for="item in carItems" :label="item.lable">
            <dict-tag v-if="item.dict" :options="dict.type[item.dict]" :value="customerForm[item.name]" />
            <span v-else>{{ customerForm[item.name] }}</span>
          </el-descriptions-item>
        </template>
      </el-descriptions>
    </st-sections>
    <st-sections title="保单信息" style="margin: 10px 10px 0 10px;">
      <el-descriptions style="margin: 10px 10px 0 10px;" :column="2" size="medium">
        <el-descriptions-item label="是否有保单">{{ customerForm['policyInfo']===1?'是':customerForm['policyInfo']===0?'否':'-' }}</el-descriptions-item>
        <template v-if="customerForm['policyInfo']===1">
          <el-descriptions-item v-for="item in policyItems" :label="item.lable">
            <dict-tag v-if="item.dict" :options="dict.type[item.dict]" :value="customerForm[item.name]" />
            <span v-else>{{ customerForm[item.name] }}</span>
          </el-descriptions-item>
        </template>
      </el-descriptions>
    </st-sections>
    <st-sections title="信用信息" style="margin: 10px 10px 0 10px;">
      <el-descriptions style="margin: 10px 10px 0 10px;" :column="2" size="medium">
        <el-descriptions-item label="是否有信用卡">{{ customerForm['creditCardInfo']===1?'是':customerForm['creditCardInfo']===0?'否':'-' }}</el-descriptions-item>
        <template v-if="customerForm['creditCardInfo']===1">
          <el-descriptions-item v-for="item in creditCardItems" :label="item.lable">
            <dict-tag v-if="item.dict" :options="dict.type[item.dict]" :value="customerForm[item.name]" />
            <span v-else>{{ customerForm[item.name] }}</span>
          </el-descriptions-item>
        </template>
      </el-descriptions>
    </st-sections>
    <st-sections title="负债信息" style="margin: 10px 10px 0 10px;">
      <el-descriptions style="margin: 10px 10px 0 10px;" :column="2" size="medium">
        <el-descriptions-item v-for="item in loanItems" :label="item.lable">
          <dict-tag v-if="item.dict" :options="dict.type[item.dict]" :value="customerForm[item.name]" />
          <span v-else>{{ customerForm[item.name] }}</span>
        </el-descriptions-item>
      </el-descriptions>
    </st-sections>
    <st-sections title="需求信息" style="margin: 10px 10px 0 10px;">
      <el-descriptions style="margin: 10px 10px 0 10px;" :column="2" size="medium">
        <el-descriptions-item v-for="item in needItems" :label="item.lable">
          <dict-tag v-if="item.dict" :options="dict.type[item.dict]" :value="customerForm[item.name]" />
          <span v-else>{{ customerForm[item.name] }}</span>
        </el-descriptions-item>
      </el-descriptions>
    </st-sections>
    <st-sections title="系统信息" style="margin: 10px 10px 0 10px;">
      <el-descriptions style="margin: 10px 10px 0 10px;" :column="2" size="medium">
        <el-descriptions-item v-for="item in systemItems" :label="item.lable">
          {{ customerForm[item.name] }}
        </el-descriptions-item>
      </el-descriptions>
    </st-sections>
  </div>
</template>
<script>
export default {
  props: {
    customerForm: {
      type: Object,
      default: function() {
        return {};
      }
    }
  },
  dicts: [
    "crm_yes_no", "crm_customer_star", "crm_customer_status", "crm_customer_professional_type", "crm_customer_pay_type", "crm_has_not", "crm_loan_type", "crm_customer_house_status", "crm_marry", "crm_customer_house_type", "crm_customer_car_status", "crm_policy_pay_type"
  ],
  data() {
    return {
      baseItems: [
        {
          "lable": "客户名称",
          "name": "name"
        },
        {
          "lable": "客户星级",
          "name": "level",
          dict: "crm_customer_star"
        },
        {
          "lable": "婚姻状态",
          "name": "maritalStatus",
          dict: "crm_marry"
        },
        {
          lable: "爱人是否知晓",
          name: "otherKnow",
          dict: "crm_yes_no"
        },
        {
          lable: "芝麻信用分",
          name: "sesameCredit"
        },
        {
          lable: "微粒贷",
          name: "microLoans"
        },
        {
          lable: "是否法人",
          name: "legalPerson",
          dict: "crm_yes_no"
        },
        {
          lable: "年份",
          name: "appliYear"
        },
        {
          lable: "社保年份",
          name: "socialSecurityYear"
        },
        {
          lable: "社保金额",
          name: "socialSecurityMoney"
        },
        {
          lable: "公积金年份",
          name: "accFundYear"
        },
        {
          lable: "公积金金额",
          name: "accFundMoney"
        },
        {
          lable: "客户状态",
          name: "status",
          dict: "crm_customer_status"
        }
      ],
      systemItems: [
        {
          lable: "创建人",
          name: "createdBy"
        },
        {
          lable: "创建时间",
          name: "createdTime"
        },
        {
          lable: "更新人",
          name: "updatedBy"
        },
        {
          lable: "更新时间",
          name: "updatedTime"
        }
      ],
      infoItems: [
        {
          lable: "单位性质",
          name: "professionalType",
          dict: "crm_customer_professional_type"
        },
        {
          lable: "工资",
          name: "payMoney"
        },
        {
          lable: "发薪方式",
          name: "payType",
          dict: "crm_customer_pay_type"
        }
      ],
      propertyItems: [
        {
          lable: "房屋状态",
          name: "propertyStatus",
          dict: "crm_customer_house_status"
        },
        {
          lable: "面积",
          name: "area"
        },
        {
          lable: "价值",
          name: "worth"
        },
        {
          lable: "房屋拥有",
          name: "ownerType",
          dict: "crm_customer_house_type"
        },
        {
          lable: "房屋所在城市",
          name: "houseCity"
        },
        {
          lable: "按揭多久",
          name: "mortgageTime"
        },
        {
          lable: "月供",
          name: "mortgageMoney"
        },
        {
          lable: "按揭银行",
          name: "mortgageBank"
        }
      ],
      carItems: [
        {
          lable: "车辆状态",
          name: "carStatus",
          dict: "crm_customer_car_status"
        },
        {
          lable: "年限",
          name: "carTermYear"
        },
        {
          lable: "裸车价",
          name: "carPrice"
        },
        {
          lable: "所在城市",
          name: "carCity"
        },
        {
          lable: "品牌型号",
          name: "carBrand"
        },
        {
          lable: "按揭多久",
          name: "carMortgageTime"
        },
        {
          lable: "月供",
          name: "carMortgageMoney"
        },
        {
          lable: "按揭银行",
          name: "carMortgageBank"
        }
      ],
      policyItems: [
        {
          lable: "投保公司",
          name: "insuranceCompany"
        },
        {
          lable: "支付方式",
          name: "insurancePayType",
          dict: "crm_policy_pay_type"
        },
        {
          lable: "缴了几年几次",
          name: "yearTime"
        }
      ],
      creditCardItems: [
        {
          lable: "信用卡总额",
          name: "creditCardMoney"
        },
        {
          lable: "有无逾期",
          name: "creditCardLateStatus",
          dict: "crm_has_not"
        }
      ],
      loanItems: [
        {
          lable: "贷款总额",
          name: "loanMoney"
        },
        {
          lable: "有无逾期",
          name: "lateStatus",
          dict: "crm_has_not"
        }
      ],
      needItems: [
        {
          lable: "资金需求",
          name: "createdBy"
        },
        {
          lable: "贷款类型",
          name: "createdTime",
          dict: "crm_loan_type"
        }
      ]
    };
  }
};
</script>
<style scoped lang="scss">
.detail-container{
  height: calc(100vh - 273px);
  overflow: auto;
}

::v-deep .el-descriptions--medium:not(.is-bordered) .el-descriptions-item__cell{
  padding-bottom: 20px;
}
</style>
