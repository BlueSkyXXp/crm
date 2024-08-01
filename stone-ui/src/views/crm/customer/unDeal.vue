<template>
  <table-panel title="我的未处理客户">
    <div slot="search">
      <el-form v-show="showSearch" ref="queryForm" :model="queryParams" label-position="right" :inline="true" label-width="150px">
        <el-form-item label="状态" prop="status">
          <el-select v-model="queryParams.status" placeholder="请选择状态" style="width: 240px" clearable>
            <el-option
              v-for="(item, index) in dict.type.crm_customer_status"
              :key="index"
              :label="item.label"
              :value="item.value"
              :disabled="item.disabled"
            />
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
          <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
        </el-form-item>
      </el-form>
    </div>
    <div slot="btm">
      <right-toolbar :show-search.sync="showSearch" @queryTable="getList" />
    </div>
    <div slot="content">
      <el-table v-loading="loading" border :height="tableHeight" :data="customerList" @selection-change="handleSelectionChange">
        <div slot="empty"><st-empty /></div>
        <el-table-column type="selection" width="55" align="center" />
        <el-table-column label="ID" align="center" prop="id" />
        <el-table-column label="客户名称" align="center" prop="name">
          <template slot-scope="scope">
            <el-button type="text" @click="handleDetail(scope.row)">
              {{ scope.row.name }}
            </el-button>
          </template>
        </el-table-column>
        <el-table-column label="性别" align="center" prop="sex">
          <template slot-scope="scope">
            <dict-tag :options="dict.type.sys_user_sex" :value="scope.row.sex" />
          </template>
        </el-table-column>
        <el-table-column label="状态" align="center" prop="status">
          <template slot-scope="scope">
            <dict-tag :options="dict.type.crm_customer_status" :value="scope.row.status" />
          </template>
        </el-table-column>
        <el-table-column label="来源" align="center" prop="source" />
        <el-table-column label="星级" align="center" prop="level">
          <template slot-scope="scope">
            <dict-tag :options="dict.type.crm_customer_star" :value="scope.row.level" />
          </template>
        </el-table-column>
        <el-table-column label="备注" width="200" align="center" prop="remark">
          <template slot-scope="scope">
            <div style="white-space: pre-wrap">{{ scope.row.remark }}</div>
          </template>
        </el-table-column>
        <el-table-column label="房" align="center" prop="propertyInfo">
          <template slot-scope="scope">
            <dict-tag :options="dict.type.crm_has_not" :value="scope.row.propertyInfo" />
          </template>
        </el-table-column>
        <el-table-column label="车" align="center" prop="carInfo">
          <template slot-scope="scope">
            <dict-tag :options="dict.type.crm_has_not" :value="scope.row.carInfo" />
          </template>
        </el-table-column>
        <el-table-column label="保单" align="center" prop="policyInfo">
          <template slot-scope="scope">
            <dict-tag :options="dict.type.crm_has_not" :value="scope.row.policyInfo" />
          </template>
        </el-table-column>
        <el-table-column label="社保" align="center" prop="socialSecurity">
          <template slot-scope="scope">
            <dict-tag :options="dict.type.crm_has_not" :value="scope.row.socialSecurity" />
          </template>
        </el-table-column>
        <el-table-column label="公积金" align="center" prop="accFund">
          <template slot-scope="scope">
            <dict-tag :options="dict.type.crm_has_not" :value="scope.row.accFund" />
          </template>
        </el-table-column>
        <el-table-column label="申请额度" align="center" prop="loanMoney" />
        <el-table-column label="最近处理时间" align="center" prop="lastRemarkTime" />
        <el-table-column label="申请时间" align="center" prop="createTime" />
        <el-table-column label="分配时间" align="center" prop="lastAssignTime" />
        <el-table-column label="城市" align="center" prop="city" />
        <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
          <template slot-scope="scope">
            <el-button
              v-hasPermi="['crm:customer:edit']"
              size="mini"
              type="text"
              icon="el-icon-edit"
              @click="handleUpdate(scope.row)"
            >修改</el-button>
            <el-button
              v-hasPermi="['crm:customer:remove']"
              size="mini"
              type="text"
              icon="el-icon-delete"
              @click="handleDelete(scope.row)"
            >删除</el-button>
          </template>
        </el-table-column>
      </el-table>
      <!-- 添加或修改客户对话框 -->
      <st-dialog v-if="open" :title="title" height="650px" width="90%" append-to-body @close="cancel" @save="submitForm">
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
                    :value="item.value"
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
                <el-date-picker v-model="form.appliYear" type="year" value-format="yyyy" placeholder="选择年" />
              </el-form-item>
            </el-col>
            <el-col :span="7">
              <el-form-item label="社保年份：" prop="socialSecurityYear">
                <el-date-picker v-model="form.socialSecurityYear" type="year" value-format="yyyy" placeholder="选择年" />
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
                <el-date-picker v-model="form.accFundYear" type="year" value-format="yyyy" placeholder="选择年" />
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
                <el-select v-model="form.loanType" placeholder="请选择贷款类型" style="display: block">
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
      <detail v-if="showDetail" ref="detailRef" :params="detailParams" @close="showDetail = false" />
    </div>
    <div slot="footer">
      <pagination :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize" @pagination="getList" />
    </div>
  </table-panel>
</template>
<script>
import { listUnprocessCustomer, getCustomer, delCustomer, addCustomer, updateCustomer } from "@/api/crm/customer";
import detail from "./detail/index";
import StDistpicker from "@/components/StDistpicker";
import rTitle from "@/components/title";
export default {
  name: "Customer",
  components: {
    StDistpicker,
    detail,
    rTitle
  },
  dicts: ["crm_customer_follow_times", "crm_customer_star", "crm_loan_type", "crm_customer_status", "sys_user_sex", "crm_has_not"],
  data() {
    return {
      showDetail: false,
      areaData: [],
      activeName: "first",
      tableHeight: undefined,
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 客户表格数据
      customerList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      field105Options: [
        {
          label: "选项一",
          value: 1
        },
        {
          label: "选项二",
          value: 2
        }
      ],
      checkUserId: "",
      customerValue: "phone",
      customerValueOptions: [
        {
          label: "手机号码",
          value: "phone"
        },
        {
          label: "ID",
          value: "id"
        },
        {
          label: "姓名",
          value: "name"
        }
      ],
      loanValue: "carInfo",
      loanValueOptions: [
        {
          label: "车",
          value: "carInfo"
        },
        {
          label: "房",
          value: "propertyInfo"
        },
        {
          label: "保单",
          value: "policyInfo"
        }
      ],
      timeValue: "lastRemarkTime",
      timeValueOptions: [
        {
          label: "最近处理时间",
          value: "lastRemarkTime"
        },
        {
          label: "申请时间",
          value: "createTime"
        },
        {
          label: "原始分配时间",
          value: "firstAssignTime"
        },
        {
          label: "最近分配时间",
          value: "lastAssignTime"
        }
      ],
      // pickerOptions: {
      //   shortcuts: [{
      //     text: '今天',
      //     onClick(picker) {
      //       picker.$emit('pick', new Date());
      //     }
      //   }, {
      //     text: '昨天',
      //     onClick(picker) {
      //       const date = new Date();
      //       date.setTime(date.getTime() - 3600 * 1000 * 24);
      //       picker.$emit('pick', date);
      //     }
      //   }, {
      //     text: '一周前',
      //     onClick(picker) {
      //       const date = new Date();
      //       date.setTime(date.getTime() - 3600 * 1000 * 24 * 7);
      //       picker.$emit('pick', date);
      //     }
      //   }]
      // },
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {},
      detailParams: {}
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
  created() {
    this.getList();
  },
  methods: {
    handleClick(tab, event) {
      console.log(tab, event);
    },
    handleDetail(row) {
      this.showDetail = true;
      this.detailParams = row;
      /* this.$nextTick(()=>{
        this.$refs.detailRef.show(row);
      })*/
    },
    /** 查询客户列表 */
    getList() {
      this.loading = true;
      listUnprocessCustomer(this.queryParams).then((response) => {
        this.customerList = response.rows;
        this.total = response.total;
        this.loading = false;
        this.$nextTick(() => {
          // this.tableHeight = this.getTableHeight();
        });
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.areaData.length = 0;
      this.form = {};
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.queryParams = { pageNum: 1, pageSize: 10 };
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map((item) => item.id);
      this.single = selection.length !== 1;
      this.multiple = !selection.length;
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加客户";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const customerId = row.id || this.ids;
      getCustomer(customerId).then((response) => {
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
        this.open = true;
        this.title = "修改客户";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate((valid) => {
        if (valid) {
          if (this.form.id != null) {
            updateCustomer(this.form).then((response) => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addCustomer(this.form).then((response) => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const customerIds = row.id || this.ids;
      this.$modal
        .confirm('是否确认删除客户编号为"' + customerIds + '"的数据项？')
        .then(function() {
          return delCustomer(customerIds);
        })
        .then(() => {
          this.getList();
          this.$modal.msgSuccess("删除成功");
        })
        .catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download(
        "crm/customer/export",
        {
          ...this.queryParams
        },
        `customer_${new Date().getTime()}.xlsx`
      );
    }
  }
};
</script>
<style scoped lang="scss">
.el-form {
  // text-align: center;
}
.el-select {
  width: 100%;
}
.el-cascader {
  width: 100%;
}
.el-date-editor {
  width: 100%;
}
::v-deep .el-tabs--border-card > .el-tabs__content {
  padding: 0px !important;
}
</style>
