<template>
  <TablePanel title="审件管理">
    <div slot="btm">
      <el-button v-hasPermi="['crm:approval_info:add']" type="primary" plain icon="el-icon-plus" @click="handleAdd">新增</el-button>
      <el-button
        v-hasPermi="['crm:approval_info:edit']"
        type="success"
        plain
        icon="el-icon-edit"
        :disabled="single"
        @click="handleUpdate"
      >修改</el-button>
      <el-button
        v-hasPermi="['crm:approval_info:remove']"
        type="danger"
        plain
        icon="el-icon-delete"
        :disabled="multiple"
        @click="handleDelete"
      >删除</el-button>
      <el-button
        v-hasPermi="['crm:approval_info:export']"
        type="warning"
        plain
        icon="el-icon-download"
        @click="handleExport"
      >导出</el-button>
      <right-toolbar :show-search.sync="showSearch" @queryTable="getList" />
    </div>
    <div slot="content">
      <el-form v-show="showSearch" ref="queryForm" :model="queryParams" :inline="true" label-width="100px">
        <el-form-item label="客户姓名" prop="customerName">
          <el-input v-model="queryParams.customerName" placeholder="请输入客户姓名" clearable @keyup.enter.native="handleQuery" />
        </el-form-item>
        <el-form-item label="银行经理" prop="bankManager">
          <el-input v-model="queryParams.bankManager" placeholder="请输入银行经理" clearable @keyup.enter.native="handleQuery" />
        </el-form-item>
        <el-form-item label="贷款顾问" prop="userId">
          <el-input v-model="queryParams.userId" placeholder="请输入贷款顾问" clearable @keyup.enter.native="handleQuery" />
        </el-form-item>
        <el-form-item label="银行" prop="bank">
          <el-input v-model="queryParams.bank" placeholder="请输入银行" clearable @keyup.enter.native="handleQuery" />
        </el-form-item>
        <!-- <el-form-item label="部门" prop="deptName">
        <el-input
          v-model="queryParams.deptName"
          placeholder="请输入部门"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item> -->
        <el-form-item label="审件状态" prop="status">
          <el-select v-model="queryParams.status" placeholder="请选择" style="width: 140px" clearable>
            <el-option
              v-for="(item, index) in dict.type.crm_approval_status"
              :key="index"
              :label="item.label"
              :value="item.value"
              :disabled="item.disabled"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="审件类型" prop="type">
          <el-select v-model="queryParams.type" placeholder="请选择" style="width: 140px" clearable>
            <el-option
              v-for="(item, index) in dict.type.crm_loan_type"
              :key="index"
              :label="item.label"
              :value="item.value"
              :disabled="item.disabled"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="查询时间范围" prop="queryTime">
          <el-date-picker
            v-model="queryParams.queryTime"
            clearable
            start-placeholder="开始日期"
            end-placeholder="结束日期"
            value-format="timestamp"
            type="datetimerange"
          />
        </el-form-item>
        <el-form-item>
          <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
          <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
        </el-form-item>
      </el-form>

      <el-table v-loading="loading" :data="infoList" @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55" align="center" />
        <el-table-column label="审件ID" align="center" prop="id">
          <template slot-scope="scope">
            <el-button type="text" @click="handleDetail(scope.row)">
              {{ scope.row.id }}
            </el-button>
          </template>
        </el-table-column>
        <el-table-column label="进件日期" align="center" prop="recdate" width="100" />
        <el-table-column label="单件类型" align="center" prop="type">
          <template slot-scope="scope">
            <dict-tag :options="dict.type.crm_loan_type" :value="scope.row.type" />
          </template>
        </el-table-column>
        <el-table-column label="跟进人" align="center" prop="userName" />
        <el-table-column label="客户id" align="center" prop="customerId" />
        <el-table-column label="客户姓名" align="center" prop="customerName" />
        <el-table-column label="审件状态" align="center" prop="status">
          <template slot-scope="scope">
            <dict-tag :options="dict.type.crm_approval_status" :value="scope.row.status" />
          </template>
        </el-table-column>
        <el-table-column label="付款方式" align="center" prop="payType">
          <template slot-scope="scope">
            <dict-tag :options="dict.type.crm_customer_paytype" :value="scope.row.payType" />
          </template>
        </el-table-column>
        <el-table-column label="进件银行" align="center" prop="approvalBank" />
        <el-table-column label="银行经理" align="center" prop="bankManager" />
        <el-table-column label="费率" align="center" prop="rates" />
        <el-table-column label="进件额度" align="center" prop="money" />
        <el-table-column label="批款额度" align="center" prop="approMoney" />
        <el-table-column label="收款额度" align="center" prop="collectMoney" />
        <el-table-column label="收款日期" align="center" prop="collectDate" width="100" />
        <el-table-column label="成本费用" align="center" prop="costMoney" />
        <el-table-column label="诚意金" align="center" prop="sincerityMoney" />
        <el-table-column label="利润" align="center" prop="profit" />
        <el-table-column label="收款银行" align="center" prop="collectBank" />
        <el-table-column label="备注信息" align="center" prop="remark" width="300" :show-overflow-tooltip="true" />
        <el-table-column label="审批人" align="center" prop="operatorName" />
        <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
          <template slot-scope="scope">
            <el-button
              v-hasPermi="['crm:approval_info:audit']"
              size="mini"
              type="text"
              icon="el-icon-check"
              @click="handleApprove(scope.row)"
            >审批</el-button>
            <el-button
              v-hasPermi="['crm:approval_info:edit']"
              size="mini"
              type="text"
              icon="el-icon-edit"
              @click="handleUpdate(scope.row)"
            >修改</el-button>
          </template>
        </el-table-column>
      </el-table>

      <pagination
        v-show="total > 0"
        :total="total"
        :page.sync="queryParams.pageNum"
        :limit.sync="queryParams.pageSize"
        @pagination="getList"
      />

      <!-- 添加或修改审件对话框 -->
      <st-dialog
        v-if="open"
        :title="title"
        height="80%"
        :show-cancel="!disable"
        :show-confirm="!disable"
        append-to-body
        @close="cancel"
        @save="submitForm"
      >
        <el-form ref="form" :model="form" :rules="rules" :disabled="disable" label-width="80px">
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
          <el-form-item
            label="进件额度"
            prop="money"
            :rules="[
              {
                pattern: /^\d+(\.\d+)?$/,
                message: '请输入有效的数字',
                trigger: 'change'
              }
            ]"
          >
            <el-input v-model="form.money" placeholder="请输入进件额度(元)" @input="handleInputMoney" />
          </el-form-item>
          <el-form-item
            label="批款额度"
            prop="approMoney"
            :rules="[
              {
                pattern: /^\d+(\.\d+)?$/,
                message: '请输入有效的数字',
                trigger: 'change'
              }
            ]"
          >
            <el-input v-model="form.approMoney" placeholder="请输入批款额度(元)" @input="handleInputApproMoney" />
          </el-form-item>
          <el-form-item
            label="收款额度"
            prop="collectMoney"
            :rules="[
              { required: true, message: '请输入收款额度', trigger: 'blur' },
              {
                pattern: /^\d+(\.\d+)?$/,
                message: '请输入有效的数字',
                trigger: 'change'
              }
            ]"
          >
            <el-input v-model.number="form.collectMoney" placeholder="请输入收款额度(元)" @input="handleInputCollectMoney" />
          </el-form-item>
          <el-form-item label="收款日期" prop="collectDate">
            <el-date-picker v-model="form.collectDate" clearable value-format="yyyy-MM-dd" type="date" placeholder="选择日期" />
          </el-form-item>
          <!--        <el-form-item label="成本费用" prop="costMoney">-->
          <!--          <el-input v-model="form.costMoney" placeholder="请输入成本费用" />-->
          <!--        </el-form-item>-->
          <el-form-item
            label="诚意金"
            prop="sincerityMoney"
            :rules="[
              {
                pattern: /^\d+(\.\d+)?$/,
                message: '请输入有效的数字',
                trigger: 'change'
              }
            ]"
          >
            <el-input v-model="form.sincerityMoney" placeholder="请输入诚意金(元)" @input="handleInputSincerityMoney" />
          </el-form-item>
          <!-- <el-form-item label="利润" prop="profit">
          <el-input v-model="form.profit" placeholder="请输入利润" />
        </el-form-item> -->
          <el-form-item label="收款银行" prop="collectBank">
            <el-input v-model="form.collectBank" placeholder="请输入收款银行" />
          </el-form-item>
          <el-form-item label="备注信息" prop="remark">
            <el-input v-model="form.remark" type="textarea" autosize placeholder="请输入内容" class="full-width-textarea" />
          </el-form-item>
        </el-form>
        <!-- 审核才有的 -->
        <div v-if="disable" slot="footer" class="dialog-footer">
          <el-form ref="form" :model="form" inline>
            <el-form-item label="请选择状态：" prop="status">
              <el-select v-model="form.status" placeholder="请选择状态" clearable>
                <el-option
                  v-for="(item, index) in dict.type.crm_approval_status"
                  :key="index"
                  :label="item.label"
                  :value="parseInt(item.value)"
                  :disabled="item.disabled"
                />
              </el-select>
            </el-form-item>
            <el-form-item
              label="成本费用："
              prop="costMoney"
              :rules="[
                { required: true, message: '请输入成本', trigger: 'blur' },
                {
                  pattern: /^\d+(\.\d+)?$/,
                  message: '请输入有效的数字',
                  trigger: 'change'
                }
              ]"
            >
              <el-input v-model="form.costMoney" placeholder="请输入成本费用(元)" @input="handleInputCostMoney" />
            </el-form-item>
            <el-form-item style="margin-left: 80px">
              <el-button type="primary" @click="auditForm">审核</el-button>
              <el-button @click="cancel">取消</el-button>
            </el-form-item>
          </el-form>
        </div>
      </st-dialog>
      <info-detail ref="infoDetail" />
    </div>
  </TablePanel>
</template>

<script>
import { listInfo, getInfo, delInfo, addInfo, updateInfo, auditInfo } from "@/api/crm/info";
import infoDetail from "./detail.vue";

export default {
  name: "Info",
  dicts: ["crm_approval_status", "crm_loan_type", "crm_customer_paytype"],
  components: {
    infoDetail
  },
  data() {
    return {
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
      // 审件表格数据
      infoList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        queryTime: [new Date(new Date().getFullYear(), new Date().getMonth(), 1).setHours(0, 0, 0, 0), new Date().getTime()]
      },
      // 表单不可选状态
      disable: false,
      // 表单参数
      form: {},
      // 表单校验
      rules: {}
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询审件列表 */
    getList() {
      this.loading = true;
      listInfo(this.queryParams).then((response) => {
        this.infoList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {};
      this.resetForm("form");
    },
    /** 点击详情 */
    handleDetail(row) {
      this.$refs["infoDetail"].show(row);
    },

    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
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
      this.disable = false;
      this.reset();
      this.open = true;
      this.title = "添加审件";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.disable = false;
      this.reset();
      const id = row.id || this.ids;
      getInfo(id).then((response) => {
        this.form = response.data;
        this.open = true;
        this.title = "修改审件";
      });
    },
    /** 审核按钮操作 */
    // todo 审核显示详情🙅修改
    handleApprove(row) {
      this.reset();
      const id = row.id || this.ids;
      this.disable = true;
      getInfo(id).then((response) => {
        this.form = response.data;
        this.open = true;
        this.title = "审核审件";
      });
    },
    handleInputCollectMoney(value) {
      // 使用正则表达式替换非数字字符，这里我们简单地替换掉非数字和小数点
      // 注意：这里假设你也允许小数输入
      this.form.collectMoney = value.replace(/[^\d.]/g, "");

      // 如果你的输入框不应该允许前导零（对于整数部分），你可以添加额外的逻辑来处理
      // 例如：去除小数点前的0（假设我们允许小数）
      if (this.form.collectMoney && this.form.collectMoney.startsWith(".")) {
        this.form.collectMoney = "0" + this.form.collectMoney;
      }

      // 或者如果你完全不允许小数，并且想去除小数点及其后的所有内容
      // this.form.collectMoney = this.form.collectMoney.split('.')[0];
    },

    handleInputApproMoney(value) {
      // 使用正则表达式替换非数字字符，这里我们简单地替换掉非数字和小数点
      // 注意：这里假设你也允许小数输入
      this.form.approMoney = value.replace(/[^\d.]/g, "");

      // 如果你的输入框不应该允许前导零（对于整数部分），你可以添加额外的逻辑来处理
      // 例如：去除小数点前的0（假设我们允许小数）
      if (this.form.approMoney && this.form.approMoney.startsWith(".")) {
        this.form.approMoney = "0" + this.form.approMoney;
      }

      // 或者如果你完全不允许小数，并且想去除小数点及其后的所有内容
      // this.form.approMoney = this.form.approMoney.split('.')[0];
    },

    handleInputSincerityMoney(value) {
      // 使用正则表达式替换非数字字符，这里我们简单地替换掉非数字和小数点
      // 注意：这里假设你也允许小数输入
      this.form.sincerityMoney = value.replace(/[^\d.]/g, "");

      // 如果你的输入框不应该允许前导零（对于整数部分），你可以添加额外的逻辑来处理
      // 例如：去除小数点前的0（假设我们允许小数）
      if (this.form.sincerityMoney && this.form.sincerityMoney.startsWith(".")) {
        this.form.sincerityMoney = "0" + this.form.sincerityMoney;
      }

      // 或者如果你完全不允许小数，并且想去除小数点及其后的所有内容
      // this.form.sincerityMoney = this.form.sincerityMoney.split('.')[0];
    },
    handleInputCostMoney(value) {
      // 使用正则表达式替换非数字字符，这里我们简单地替换掉非数字和小数点
      // 注意：这里假设你也允许小数输入
      this.form.costMoney = value.replace(/[^\d.]/g, "");

      // 如果你的输入框不应该允许前导零（对于整数部分），你可以添加额外的逻辑来处理
      // 例如：去除小数点前的0（假设我们允许小数）
      if (this.form.costMoney && this.form.costMoney.startsWith(".")) {
        this.form.costMoney = "0" + this.form.costMoney;
      }

      // 或者如果你完全不允许小数，并且想去除小数点及其后的所有内容
      // this.form.costMoney = this.form.costMoney.split('.')[0];
    },

    handleInputMoney(value) {
      // 使用正则表达式替换非数字字符，这里我们简单地替换掉非数字和小数点
      // 注意：这里假设你也允许小数输入
      this.form.money = value.replace(/[^\d.]/g, "");

      // 如果你的输入框不应该允许前导零（对于整数部分），你可以添加额外的逻辑来处理
      // 例如：去除小数点前的0（假设我们允许小数）
      if (this.form.money && this.form.money.startsWith(".")) {
        this.form.money = "0" + this.form.money;
      }

      // 或者如果你完全不允许小数，并且想去除小数点及其后的所有内容
      // this.form.money = this.form.money.split('.')[0];
    },

    /** 提交审核按钮 */
    auditForm() {
      this.$refs["form"].validate((valid) => {
        if (valid) {
          if (this.form.id != null) {
            auditInfo({
              id: this.form.id,
              status: this.form.status,
              costMoney: this.form.costMoney
            }).then((response) => {
              this.$modal.msgSuccess("审核成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate((valid) => {
        if (valid) {
          if (this.form.id != null) {
            updateInfo(this.form).then((response) => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addInfo(this.form).then((response) => {
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
      const ids = row.id || this.ids;
      this.$modal
        .confirm('是否确认删除审件编号为"' + ids + '"的数据项？')
        .then(function() {
          return delInfo(ids);
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
        "crm/info/export",
        {
          ...this.queryParams
        },
        `info_${new Date().getTime()}.xlsx`
      );
    }
  }
};
</script>
