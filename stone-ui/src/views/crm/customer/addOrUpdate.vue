<template>
  <!-- 添加或修改客户对话框 -->
  <st-dialog
    v-if="open"
    :title="title"
    height="650px"
    width="90%"
    append-to-body
    @close="cancel"
    @save="submitForm"
  >
    <el-form ref="form" :model="form" :rules="rules" label-width="120px">
      <r-title title="基本信息" :is-icon="false" />
      <el-row>
        <el-col :span="7">
          <el-form-item label="客户名称：" prop="name">
            <el-input v-model="form.name" placeholder="请输入客户名称" />
          </el-form-item>
        </el-col>
        <el-col :span="7">
          <el-form-item label="手机电话：" prop="phone">
            <el-input v-model="form.phone" placeholder="请输入手机电话" :disabled="form.id" />
          </el-form-item>
        </el-col>
        <el-col :span="7">
          <!-- <el-form-item label="单位座机：" prop="phone">
            <el-input v-model="form.phone" placeholder="请输入单位座机" />
          </el-form-item> -->
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="7">
          <el-form-item label="年龄：" prop="age">
            <el-input v-model="form.age" placeholder="请输入年龄" />
          </el-form-item>
        </el-col>
        <el-col :span="7">
          <el-form-item label="性别：" prop="sex">
            <el-radio-group v-model="form.sex">
              <el-radio :label="0">男</el-radio>
              <el-radio :label="1">女</el-radio>
            </el-radio-group>
          </el-form-item>
        </el-col>
        <el-col :span="7">
          <el-form-item label="户籍：" prop="region">
            <el-input v-model="form.region" placeholder="请输入户籍" />
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="7">
          <el-form-item label="婚姻状态：" prop="maritalStatus">
            <el-radio-group v-model="form.maritalStatus">
              <el-radio :label="0">未婚</el-radio>
              <el-radio :label="1">已婚</el-radio>
            </el-radio-group>
          </el-form-item>
        </el-col>
        <el-col :span="7">
          <el-form-item label="爱人是否知晓：" prop="otherKnow">
            <el-radio-group v-model="form.otherKnow">
              <el-radio :label="1">是</el-radio>
              <el-radio :label="0">否</el-radio>
            </el-radio-group>
          </el-form-item>
        </el-col>
        <el-col :span="7">
          <el-form-item label="芝麻信用分：" prop="sesameCredit">
            <el-input v-model="form.sesameCredit" placeholder="请输入芝麻信用分" />
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="7">
          <el-form-item label="微粒贷：" prop="microLoans">
            <el-input v-model="form.microLoans" placeholder="请输入微粒贷" />
          </el-form-item>
          <!-- 详情才有客户状态 -->
          <!-- <el-form-item label="客户状态：" prop="importance">
            <el-select v-model="form.type" placeholder="请输入客户类型">
              <el-option
                v-for="dict in dict.type.customer_type"
                :key="dict.value"
                :label="dict.label"
                :value="dict.value"
              ></el-option>
            </el-select>
          </el-form-item> -->
        </el-col>
        <el-col :span="7">
          <el-form-item label="客户星级：" prop="level">
            <el-select v-model="form.level" placeholder="请选择星级">
              <el-option
                v-for="(item, index) in dict.type.crm_customer_star"
                :key="index"
                :label="item.label"
                :value="parseInt(item.value)"
                :disabled="item.disabled"
              />
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="7">
          <el-form-item label="是否法人：" prop="legalPerson">
            <el-radio-group v-model="form.legalPerson">
              <el-radio :label="1">是</el-radio>
              <el-radio :label="0">否</el-radio>
            </el-radio-group>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="7">
          <el-form-item label="年份：" prop="appliYear">
            <el-date-picker
              v-model="form.appliYear"
              type="year"
              value-format="yyyy"
              placeholder="选择年"
            />
          </el-form-item>
        </el-col>
        <el-col :span="7">
          <el-form-item label="社保年份：" prop="socialSecurityYear">
            <el-date-picker
              v-model="form.socialSecurityYear"
              type="year"
              value-format="yyyy"
              placeholder="选择年"
            />
          </el-form-item>
        </el-col>
        <el-col :span="7">
          <el-form-item label="社保金额：" prop="socialSecurityMoney">
            <el-input v-model="form.socialSecurityMoney" placeholder="请输入社保金额" />
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="7">
          <el-form-item label="公积金年份：" prop="accFundYear">
            <el-date-picker
              v-model="form.accFundYear"
              type="year"
              value-format="yyyy"
              placeholder="选择年"
            />
          </el-form-item>
        </el-col>
        <el-col :span="7">
          <el-form-item label="公积金金额：" prop="accFundMoney">
            <el-input v-model="form.accFundMoney" placeholder="请输入公积金金额" />
          </el-form-item>
        </el-col>
      </el-row>
      <r-title title="身份信息" :is-icon="false" />
      <el-row>
        <el-col :span="22">
          <el-form-item label="职业身份：" prop="professional">
            <el-radio-group v-model="form.professional">
              <el-radio :label="0">无</el-radio>
              <el-radio :label="1">有</el-radio>
            </el-radio-group>
          </el-form-item>
        </el-col>
      </el-row>
      <r-title title="房产信息" :is-icon="false" />
      <el-row>
        <el-col :span="22">
          <el-form-item label="是否有房：" prop="propertyInfo">
            <el-radio-group v-model="form.propertyInfo">
              <el-radio :label="0">无</el-radio>
              <el-radio :label="1">有</el-radio>
            </el-radio-group>
          </el-form-item>
        </el-col>
      </el-row>
      <template v-if="form.propertyInfo">
        <el-row>
          <el-col :span="14">
            <el-form-item label="房屋状态：" prop="propertyStatus">
              <el-radio-group v-model="form.propertyStatus">
                <el-radio :label="1">全款</el-radio>
                <el-radio :label="2">按揭</el-radio>
                <el-radio :label="3">抵押</el-radio>
                <el-radio :label="4">查封</el-radio>
              </el-radio-group>
            </el-form-item>
          </el-col>
          <el-col :span="7">
            <el-form-item label="面积：" prop="area">
              <el-input v-model="form.area" placeholder="请输入面积" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="7">
            <el-form-item label="价值：" prop="worth">
              <el-input v-model="form.worth" placeholder="请输入价值" />
            </el-form-item>
          </el-col>
          <el-col :span="7">
            <el-form-item label="房屋拥有：" prop="ownerType">
              <el-radio-group v-model="form.ownerType">
                <el-radio :label="1">共有</el-radio>
                <el-radio :label="2">单独</el-radio>
              </el-radio-group>
            </el-form-item>
          </el-col>
          <el-col :span="7">
            <el-form-item label="房屋所在城市：" prop="houseCity">
              <el-input v-model="form.houseCity" placeholder="请输入房屋所在城市" />
            </el-form-item>
          </el-col>
        </el-row>
        <template v-if="form.propertyStatus === 2">
          <el-row>
            <el-col :span="7">
              <el-form-item label="按揭多久：" prop="mortgageTime">
                <el-input v-model="form.mortgageTime" placeholder="请输入月" />
              </el-form-item>
            </el-col>
            <el-col :span="7">
              <el-form-item label="月供：" prop="mortgageMoney">
                <el-input v-model="form.mortgageMoney" placeholder="请输入月供" />
              </el-form-item>
            </el-col>
            <el-col :span="7">
              <el-form-item label="按揭银行：" prop="mortgageBank">
                <el-input v-model="form.mortgageBank" placeholder="请输入按揭银行" />
              </el-form-item>
            </el-col>
          </el-row>
        </template>
      </template>
      <r-title title="车产信息" :is-icon="false" />
      <el-row>
        <el-col :span="22">
          <el-form-item label="是否有车：" prop="carInfo">
            <el-radio-group v-model="form.carInfo">
              <el-radio :label="0">无</el-radio>
              <el-radio :label="1">有</el-radio>
            </el-radio-group>
          </el-form-item>
        </el-col>
      </el-row>
      <template v-if="form.carInfo">
        <el-row>
          <el-col :span="14">
            <el-form-item label="车辆状态：" prop="carStatus">
              <el-radio-group v-model="form.carStatus">
                <el-radio :label="1">全款</el-radio>
                <el-radio :label="2">按揭</el-radio>
                <el-radio :label="3">抵押</el-radio>
                <!-- <el-radio :label="4">查封</el-radio> -->
              </el-radio-group>
            </el-form-item>
          </el-col>
          <el-col :span="7">
            <el-form-item label="年限：" prop="carTermYear">
              <el-input v-model="form.carTermYear" placeholder="请输入年限" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="7">
            <el-form-item label="裸车价：" prop="carPrice">
              <el-input v-model="form.carPrice" placeholder="请输入裸车价" />
            </el-form-item>
          </el-col>
          <el-col :span="7">
            <el-form-item label="所在城市：" prop="carCity">
              <el-input v-model="form.carCity" placeholder="请输入" />
            </el-form-item>
          </el-col>
          <el-col :span="7">
            <el-form-item label="品牌型号：" prop="carBrand">
              <el-input v-model="form.carBrand" placeholder="请输入品牌型号" />
            </el-form-item>
          </el-col>
        </el-row>
        <template v-if="form.carStatus === 2">
          <el-row>
            <el-col :span="7">
              <el-form-item label="按揭多久：" prop="carMortgageTime">
                <el-input v-model="form.carMortgageTime" placeholder="请输入月" />
              </el-form-item>
            </el-col>
            <el-col :span="7">
              <el-form-item label="月供：" prop="carMortgageMoney">
                <el-input v-model="form.carMortgageMoney" placeholder="请输入月供" />
              </el-form-item>
            </el-col>
            <el-col :span="7">
              <el-form-item label="按揭银行：" prop="carMortgageBank">
                <el-input v-model="form.carMortgageBank" placeholder="请输入按揭银行" />
              </el-form-item>
            </el-col>
          </el-row>
        </template>
      </template>
      <r-title title="保单信息" :is-icon="false" />
      <el-row>
        <el-col :span="22">
          <el-form-item label="是否有保单：" prop="policyInfo">
            <el-radio-group v-model="form.policyInfo">
              <el-radio :label="0">无</el-radio>
              <el-radio :label="1">有</el-radio>
            </el-radio-group>
          </el-form-item>
        </el-col>
      </el-row>
      <template v-if="form.policyInfo">
        <el-row>
          <el-col :span="7">
            <el-form-item label="投保公司：" prop="insuranceCompany">
              <el-input v-model="form.insuranceCompany" placeholder="请输入投保公司" />
            </el-form-item>
          </el-col>
          <el-col :span="7">
            <el-form-item label="支付方式：" prop="insurancePayType">
              <el-radio-group v-model="form.insurancePayType">
                <el-radio :label="1">年缴</el-radio>
                <el-radio :label="2">月缴</el-radio>
              </el-radio-group>
            </el-form-item>
          </el-col>
          <el-col :span="7">
            <el-form-item label="缴了几年几次：" prop="yearTime">
              <el-input v-model="form.yearTime" placeholder="请输入" />
            </el-form-item>
          </el-col>
        </el-row>
      </template>
      <r-title title="信用信息" :is-icon="false" />
      <el-row>
        <el-col :span="22">
          <el-form-item label="是否有信用卡：" prop="creditCardInfo">
            <el-radio-group v-model="form.creditCardInfo">
              <el-radio :label="0">无</el-radio>
              <el-radio :label="1">有</el-radio>
            </el-radio-group>
          </el-form-item>
        </el-col>
      </el-row>
      <template v-if="form.creditCardInfo">
        <el-row>
          <el-col :span="7">
            <el-form-item label="信用卡总额：" prop="creditCardMoney">
              <el-input v-model="form.creditCardMoney" placeholder="请输入" />
            </el-form-item>
          </el-col>
          <el-col :span="7">
            <el-form-item label="有无逾期：" prop="creditCardLateStatus">
              <el-radio-group v-model="form.creditCardLateStatus">
                <el-radio :label="0">无</el-radio>
                <el-radio :label="1">有</el-radio>
              </el-radio-group>
            </el-form-item>
          </el-col>
        </el-row>
      </template>
      <r-title title="负债信息" :is-icon="false" />
      <el-row>
        <el-col :span="11">
          <el-form-item label="贷款总额：" prop="loanMoney">
            <el-input v-model="form.loanMoney" placeholder="请输入贷款总额" />
          </el-form-item>
        </el-col>
        <el-col :span="11">
          <el-form-item label="有无逾期：" prop="lateStatus">
            <el-radio-group v-model="form.lateStatus">
              <el-radio :label="0">无</el-radio>
              <el-radio :label="1">有</el-radio>
            </el-radio-group>
          </el-form-item>
        </el-col>
      </el-row>
      <r-title title="需求信息" :is-icon="false" />
      <el-row>
        <el-col :span="11">
          <el-form-item label="资金需求：" prop="demandMoney">
            <el-input v-model="form.demandMoney" placeholder="请输入金额" />
          </el-form-item>
        </el-col>
        <el-col :span="11">
          <el-form-item label="贷款类型：" prop="loanType">
            <el-select v-model="form.loanType" placeholder="请选择贷款类型" style="display: block;">
              <el-option
                v-for="(item, index) in dict.type.crm_loan_type"
                :key="index"
                :label="item.label"
                :value="parseInt(item.value)"
                :disabled="item.disabled"
              />
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>
    </el-form>
  </st-dialog>
</template>
<script>
import StDistpicker from "@/components/StDistpicker/index.vue";
import rTitle from "@/components/title";
import { updateCustomer, addCustomer, getCustomer } from "@/api/crm/customer";
export default {
  components: { StDistpicker, rTitle },
  dicts: ["crm_customer_follow_times", "crm_customer_star", "crm_loan_type", "crm_customer_status"],
  data() {
    return {
      loading: false,
      title: "",
      open: false,
      form: {},
      rules: {},
      areaData: []
    };
  },
  watch: {
    /** form表单联动 */
	  form: {
      handler: function(val) {
        if (this.form.propertyInfo == 0) {
          this.$set(this.form, "propertyStatus", null);
          this.$set(this.form, "area", null);
          this.$set(this.form, "worth", null);
          this.$set(this.form, "ownerType", null);
          this.$set(this.form, "houseCity", null);
          this.$set(this.form, "mortgageTime", null);
          this.$set(this.form, "mortgageMoney", null);
          this.$set(this.form, "mortgageBank", null);
        }
        if (this.form.carInfo == 0) {
          this.$set(this.form, "carStatus", null);
          this.$set(this.form, "carTermYear", null);
          this.$set(this.form, "carPrice", null);
          this.$set(this.form, "carCity", null);
          this.$set(this.form, "carBrand", null);
          this.$set(this.form, "carMortgageTime", null);
          this.$set(this.form, "carMortgageMoney", null);
          this.$set(this.form, "carMortgageBank", null);
        }
        if (this.form.policyInfo == 0) {
          this.$set(this.form, "insuranceCompany", null);
          this.$set(this.form, "insurancePayType", null);
          this.$set(this.form, "yearTime", null);
        }
        if (this.form.creditCardInfo == 0) {
          this.$set(this.form, "creditCardMoney", null);
          this.$set(this.form, "creditCardLateStatus", null);
        }
      },
      deep: true
    }
  },
  methods: {
    /**
     *日期时间改变
    * **/
    // datetimeChange(e){
    //   console.log(e)
    //   let _this = this
    //   _this.form.accFundYear = e
    //   _this.$forceUpdate();
    // },
    show(data) {
      this.open = true;
      this.reset();
      if (typeof data == "undefined") {
        this.title = "新增客户";
      } else {
        this.title = "修改客户";
        this.loading = true;
        getCustomer(data.id).then(response => {
          this.form = response.data;
          // if(typeof this.form.provinceId != "undefined" && this.form.provinceId != null) {
          //   this.$set(this.areaData,0,+this.form.provinceId)
          // }
          // if(typeof this.form.cityId != "undefined" && this.form.cityId != null) {
          //   this.$set(this.areaData,1,+this.form.cityId)
          // }
          // if(typeof this.form.areaId != "undefined" && this.form.areaId != null) {
          //   this.$set(this.areaData,2,+this.form.areaId)
          // }
          this.loading = false;
        });
      }
    },
    // 表单重置
    reset() {
      this.areaData.length = 0;
      this.form = {};
      this.resetForm("form");
    },
    cancel() {
      this.open = false;
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateCustomer(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.$emit("updateParams", this.form);
            });
          } else {
            addCustomer(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    }

  }
};
</script>
<style scoped lang="scss">
.el-select {
  width : 100%;
}
.el-cascader{
  width: 100%;
}
.el-date-editor{
  width: 100%;
}
</style>
