<template>
  <st-dialog
    v-show="open"
    :title="title"
    height="90%"
    width="90%"
    append-to-body
    :show-cancel="false"
    :show-confirm="false"
    @close="cancel"
  >
    <el-form ref="form" :model="form" :rules="rules" :disabled="true" label-width="80px">
      <el-form-item label="进件日期" prop="recdate">
        <el-date-picker v-model="form.recdate" clearable value-format="yyyy-MM-dd" type="date" placeholder="选择日期" />
      </el-form-item>
      <el-form-item label="单件类型" prop="type">
        <el-select v-model="form.type" placeholder="请选择单件类型">
          <el-option
            v-for="(item, index) in dict.type.crm_loan_type"
            :key="index"
            :label="item.label"
            :value="parseInt(item.value)"
            :disabled="item.disabled"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="付款方式" prop="payType">
        <el-select v-model="form.payType" placeholder="请选择付款方式">
          <el-option
            v-for="(item, index) in dict.type.crm_customer_paytype"
            :key="index"
            :label="item.label"
            :value="item.value"
            :disabled="item.disabled"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="客户ID" prop="customerId">
        <el-input v-model="form.customerId" placeholder="请输入客户ID" />
      </el-form-item>
      <el-form-item label="进件银行" prop="approvalBank">
        <el-input v-model="form.approvalBank" placeholder="请输入进件银行" />
      </el-form-item>
      <el-form-item label="银行经理" prop="bankManager">
        <el-input v-model="form.bankManager" placeholder="请输入银行经理" />
      </el-form-item>
      <el-form-item label="费率" prop="rates">
        <el-input v-model="form.rates" placeholder="请输入费率" />
      </el-form-item>
      <el-form-item label="进件额度" prop="money">
        <el-input v-model="form.money" placeholder="请输入进件额度" />
      </el-form-item>
      <el-form-item label="批款额度" prop="approMoney">
        <el-input v-model="form.approMoney" placeholder="请输入批款额度" />
      </el-form-item>
      <el-form-item label="收款额度" prop="collectMoney">
        <el-input v-model.number="form.collectMoney" placeholder="请输入收款额度" />
      </el-form-item>
      <el-form-item label="收款日期" prop="collectDate">
        <el-date-picker v-model="form.collectDate" clearable value-format="yyyy-MM-dd" type="date" placeholder="选择日期" />
      </el-form-item>
      <el-form-item label="成本费用" prop="costMoney">
        <el-input v-model="form.costMoney" placeholder="请输入成本费用" />
      </el-form-item>
      <el-form-item label="诚意金" prop="sincerityMoney">
        <el-input v-model="form.sincerityMoney" placeholder="请输入诚意金" />
      </el-form-item>
      <el-form-item label="利润" prop="profit">
        <el-input v-model="form.profit" placeholder="请输入利润" />
      </el-form-item>
      <el-form-item label="收款银行" prop="collectBank">
        <el-input v-model="form.collectBank" placeholder="请输入收款银行" />
      </el-form-item>
      <el-form-item label="备注信息" prop="remark">
        <el-input v-model="form.remark" type="textarea" autosize placeholder="请输入内容" class="full-width-textarea" />
      </el-form-item>
    </el-form>
  </st-dialog>
</template>

<script>
import { getInfo } from "@/api/crm/info";
export default {
  name: "InfoDetail",
  dicts: ["crm_approval_status", "crm_loan_type", "crm_customer_paytype"],
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
  methods: {
    show(data) {
      this.open = true;
      if (typeof data == "undefined") {
        this.title = "新增审件";
      } else {
        this.title = "审件详情";
        this.loading = true;
        getInfo(data.id).then((response) => {
          this.form = response.data;
          this.loading = false;
        });
      }
    },
    cancel() {
      this.open = false;
    }
  }
};
</script>
