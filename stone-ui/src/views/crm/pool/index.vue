<template>
  <!-- 业务范围：分配客户，查询 -->
  <TablePanel title="公共池">
    <div slot="btm">
      <right-toolbar :show-search.sync="showSearch" @queryTable="getList" />
    </div>
    <div slot="search">
      <el-form v-show="showSearch" ref="queryForm" :model="queryParams" label-position="right" :inline="true" label-width="150px">
        <!-- <el-row :gutter="20">
        <el-col :span="6"> -->
        <el-form-item label="客户" prop="customerId">
          <div class="flex">
            <el-select v-model="customerValue" placeholder="请选择" style="width: 110px" clearable>
              <el-option
                v-for="(item, index) in customerValueOptions"
                :key="index"
                :label="item.label"
                :value="item.value"
                :disabled="item.disabled"
              />
            </el-select>
            <el-input v-if="customerValue === 'phone'" v-model="queryParams.phone" placeholder="请输入" clearable style="width: 130px" />
            <el-input v-if="customerValue === 'id'" v-model="queryParams.id" placeholder="请输入" clearable style="width: 130px" />
            <el-input v-if="customerValue === 'name'" v-model="queryParams.name" placeholder="请输入" clearable style="width: 130px" />
          </div>
        </el-form-item>
        <!-- </el-col>
        <el-col :span="6"> -->
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
        <!-- </el-col>
        <el-col :span="6"> -->
        <el-form-item label="星级" prop="level">
          <el-select v-model="queryParams.level" placeholder="请选择星级" style="width: 240px" clearable>
            <el-option
              v-for="(item, index) in dict.type.crm_customer_star"
              :key="index"
              :label="item.label"
              :value="item.value"
              :disabled="item.disabled"
            />
          </el-select>
        </el-form-item>
        <!-- </el-col>
        <el-col :span="6"> -->
        <el-form-item label="来源" prop="source">
          <el-select v-model="queryParams.source" placeholder="请选择来源" style="width: 240px" clearable>
            <el-option
              v-for="(item, index) in dataSourceOptions"
              :key="index"
              :label="item.label"
              :value="item.value"
              :disabled="item.disabled"
            />
          </el-select>
        </el-form-item>
        <!-- </el-col>
        </el-row> -->
        <el-form-item label="未新增备注超过" prop="remarkDay">
          <el-input v-model="queryParams.remarkDay" placeholder="请输入" clearable style="width: 240px">
            <template slot="append">天</template>
          </el-input>
        </el-form-item>
        <el-form-item label="贷款类型" prop="loanType">
          <el-select v-model="queryParams.loanType" placeholder="请选择贷款类型" style="width: 240px" clearable>
            <el-option
              v-for="(item, index) in dict.type.crm_loan_type"
              :key="index"
              :label="item.label"
              :value="item.value"
              :disabled="item.disabled"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="贷款条件" class="flex">
          <div class="flex">
            <el-select v-model="loanValue" placeholder="请选择" style="width: 100px" clearable>
              <el-option
                v-for="(item, index) in loanValueOptions"
                :key="index"
                :label="item.label"
                :value="item.value"
                :disabled="item.disabled"
              />
            </el-select>
            <el-select v-if="loanValue === 'carInfo'" v-model="queryParams.carInfo" placeholder="请选择" style="width: 140px">
              <el-option
                v-for="(item, index) in hasOptions"
                :key="index"
                :label="item.label"
                :value="item.value"
                :disabled="item.disabled"
              />
            </el-select>
            <el-select v-if="loanValue === 'propertyInfo'" v-model="queryParams.propertyInfo" placeholder="请选择" style="width: 140px">
              <el-option
                v-for="(item, index) in hasOptions"
                :key="index"
                :label="item.label"
                :value="item.value"
                :disabled="item.disabled"
              />
            </el-select>
            <el-select v-if="loanValue === 'policyInfo'" v-model="queryParams.policyInfo" placeholder="请选择" style="width: 140px">
              <el-option
                v-for="(item, index) in hasOptions"
                :key="index"
                :label="item.label"
                :value="item.value"
                :disabled="item.disabled"
              />
            </el-select>
            <el-select v-if="loanValue === 'socialSecurity'" v-model="queryParams.socialSecurity" placeholder="请选择" style="width: 140px">
              <el-option
                v-for="(item, index) in hasOptions"
                :key="index"
                :label="item.label"
                :value="item.value"
                :disabled="item.disabled"
              />
            </el-select>
            <el-select v-if="loanValue === 'accFund'" v-model="queryParams.accFund" placeholder="请选择" style="width: 140px">
              <el-option
                v-for="(item, index) in hasOptions"
                :key="index"
                :label="item.label"
                :value="item.value"
                :disabled="item.disabled"
              />
            </el-select>
          </div>
        </el-form-item>
        <el-form-item label="备注" prop="params">
          <el-input v-model="queryParams.params" placeholder="请输入备注" clearable style="width: 240px" />
        </el-form-item>
        <!-- <el-form-item label="部门" prop="dept">
          <el-cascader v-model="depts" :options="deptOption" :props="deptProps"
          :style="{width: '100%'}" placeholder="请选择部门" clearable></el-cascader>
        </el-form-item> -->
        <el-form-item label="贷款额度" prop="demandMoney">
          <el-input v-model="queryParams.minDemandMoney" placeholder="" clearable style="width: 110px">
            <template slot="append">万</template>
          </el-input>
          ～
          <el-input v-model="queryParams.maxDemandMoney" placeholder="" clearable style="width: 110px">
            <template slot="append">万</template>
          </el-input>
        </el-form-item>
        <!-- <el-form-item label="是否跟进" prop="field105">
          <el-select v-model="queryParams.followTimes" placeholder="请选择" style="width: 240px">
            <el-option v-for="(item, index) in dict.type.crm_customer_fellow" :key="index" :label="item.label"
              :value="item.value" :disabled="item.disabled"></el-option>
          </el-select>
        </el-form-item> -->
        <el-form-item label="跟进次数" prop="followTimes">
          <el-select v-model="queryParams.followTimes" placeholder="请选择跟进次数" style="width: 240px" clearable>
            <el-option
              v-for="(item, index) in dict.type.crm_customer_follow_times"
              :key="index"
              :label="item.label"
              :value="item.value"
              :disabled="item.disabled"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="时间类型" prop="customerId" class="flex">
          <div class="flex">
            <el-select v-model="timeValue" placeholder="请选择" style="width: 130px" clearable>
              <el-option
                v-for="(item, index) in timeValueOptions"
                :key="index"
                :label="item.label"
                :value="item.value"
                :disabled="item.disabled"
              />
            </el-select>
            <el-date-picker
              v-if="timeValue === 'lastRemarkTime'"
              v-model="queryParams.lastRemarkTime"
              type="datetimerange"
              value-format="timestamp"
              start-placeholder="开始日期"
              end-placeholder="结束日期"
              align="right"
              style="width: 355px"
            />
            <el-date-picker
              v-if="timeValue === 'createTime'"
              v-model="queryParams.createTime"
              type="datetimerange"
              value-format="timestamp"
              start-placeholder="开始日期"
              end-placeholder="结束日期"
              align="right"
              style="width: 355px"
            />
            <el-date-picker
              v-if="timeValue === 'lastAssignTime'"
              v-model="queryParams.lastAssignTime"
              type="datetimerange"
              value-format="timestamp"
              start-placeholder="开始日期"
              end-placeholder="结束日期"
              align="right"
              style="width: 355px"
            />
            <el-date-picker
              v-if="timeValue === 'firstAssignTime'"
              v-model="queryParams.firstAssignTime"
              type="datetimerange"
              value-format="timestamp"
              start-placeholder="开始日期"
              end-placeholder="结束日期"
              align="right"
              style="width: 355px"
            />
          </div>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
          <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
        </el-form-item>
      </el-form>
      <div class="selectPerson bg-blue m10 p10">
        <el-form ref="queryForm" :model="queryParams" :inline="true">
          <el-form-item label="快速勾选：" prop="customerId">
            <!-- <div class="flex"> -->
            <el-cascader
              v-model="depts2"
              :options="deptOption"
              :props="deptProps"
              :style="{ width: '100%' }"
              placeholder="部门"
              clearable
            />
            <!-- </div> -->
          </el-form-item>
          <!-- <el-form-item label="检索顾问：" prop="customerId">
          <el-input
            v-model="queryParams.customerId"
            placeholder="请输入顾问"
            clearable
          />
        </el-form-item> -->
          <el-form-item>
            <el-button type="primary" icon="el-icon-search" size="mini" @click="handleUserQuery">查询</el-button>
            <!-- <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button> -->
          </el-form-item>
        </el-form>
        分配给：
        <el-radio-group v-model="checkUserId">
          <el-radio v-for="item in users" :key="item.userId" :label="item.userId">{{ item.nickName || item.userName }}</el-radio>
        </el-radio-group>
        <el-button class="ml20" type="primary" size="mini" @click="handleAssign">分配</el-button>
      </div>
    </div>

    <div slot="content">
      <el-table v-loading="loading" border :max-height="tableHeight" :data="customerList" @selection-change="handleSelectionChange">
        <div slot="empty">
          <el-empty :image="noData" :image-size="200" />
        </div>
        <el-table-column type="selection" width="55" align="center" />
        <el-table-column label="ID" align="center" prop="id" />
        <el-table-column label="客户姓名" align="center" prop="name" />
        <el-table-column label="性别" align="center" prop="sex" />
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
        <!-- <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['crm:contract:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['crm:contract:remove']"
          >删除</el-button>
        </template>
      </el-table-column> -->
      </el-table>
    </div>
    <div slot="footer">
      <pagination :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize" @pagination="getList" />
    </div>
  </TablePanel>
</template>

<script>
import {
  listPublicCustomer,
  getCustomer,
  delCustomer,
  addCustomer,
  updateCustomer,
  assignCustomer,
  optionCustomerSource
} from "@/api/crm/customer";
import noData from "@/assets/img/no_data.png";
import { deptOption, deptProps } from "@/utils/utils";
// import { hasOptions } from '@/maps/options'
import { listDept, deptUser } from "@/api/crm/dept";
import { mapGetters } from "vuex";
export default {
  name: "Contract",
  dicts: ["crm_customer_follow_times", "crm_customer_star", "crm_loan_type", "crm_customer_status", "sys_user_sex", "crm_has_not"],
  data() {
    return {
      noData: noData,
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
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {},
      depts: [],
      depts2: [],
      // 数据来源选项
      dataSourceOptions: [],
      hasOptions: [
        {
          label: "不限",
          value: undefined
        },
        {
          label: "无",
          value: 0
        },
        {
          label: "有",
          value: 1
        }
      ],
      users: [
        { text: "张三", id: 1 },
        { text: "张四", id: 2 },
        { text: "张五", id: 3 }
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
        },
        {
          label: "社保",
          value: "socialSecurity"
        },
        {
          label: "公积金",
          value: "accFund"
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
      deptOption: deptOption,
      deptProps: deptProps
    };
  },
  computed: {
    ...mapGetters(["userId", "deptId"])
  },
  created() {
    this.getList();
    this.getDataSourceOptions();
    if (this.deptId) {
      this.deptQuery();
    }
    // this.userQuery();
  },
  methods: {
    /** 查询公共池客户列表 */
    getList() {
      this.loading = true;
      if (this.depts.length > 0) {
        const length = this.depts.length;
        this.queryParams.dept = this.depts[length - 1];
      }
      listPublicCustomer(this.queryParams).then((response) => {
        this.customerList = response.rows;
        this.total = response.total;
        this.loading = false;
        this.$nextTick(() => {
          // this.tableHeight = this.getTableHeightWithTitle()
        });
      });
    },
    /** 搜索部门 */
    deptQuery() {
      listDept({ deptId: this.deptId }).then((response) => {
        this.deptOption = JSON.parse(response.data);
        this.userQuery();
      });
    },
    /** 搜索顾问 */
    userQuery() {
      if (this.depts2.length == 0) {
        this.depts2 = [this.deptId];
      }
      deptUser(this.depts2[this.depts2.length - 1]).then((response) => {
        this.users = response.rows;
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
    /** 搜索业务员按钮操作 */
    handleUserQuery() {
      this.userQuery();
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
      const customerId = row.customerId || this.ids;
      getCustomer(customerId).then((response) => {
        this.form = response.data;
        if (typeof this.form.provinceId != "undefined" && this.form.provinceId != null) {
          this.$set(this.areaData, 0, +this.form.provinceId);
        }
        if (typeof this.form.cityId != "undefined" && this.form.cityId != null) {
          this.$set(this.areaData, 1, +this.form.cityId);
        }
        if (typeof this.form.areaId != "undefined" && this.form.areaId != null) {
          this.$set(this.areaData, 2, +this.form.areaId);
        }
        this.open = true;
        this.title = "修改客户";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate((valid) => {
        if (valid) {
          if (this.form.customerId != null) {
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
      const customerIds = row.customerId || this.ids;
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
    },
    /** 分配按钮操作 */
    handleAssign() {
      // 需要选中业务员
      assignCustomer({ userId: this.checkUserId, customerIds: this.ids }).then((response) => {
        this.$modal.msgSuccess("分配成功");
        this.getList();
      });
    },
    /** 来源选项 */
    getDataSourceOptions() {
      optionCustomerSource().then((response) => {
        this.dataSourceOptions = response.rows.map((item, index) => {
          return { label: item || "不限", value: item };
        });
      });
    }
  }
};
</script>

<style scoped lang="scss">
.selectPerson {
  margin-top: 20px;
  .el-form {
    text-align: left;
    padding: 1px;
  }
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
