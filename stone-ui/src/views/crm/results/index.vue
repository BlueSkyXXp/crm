<template>
  <TablePanel title="业绩排行榜">
    <div slot="btm">
      <right-toolbar :show-search.sync="showSearch" @queryTable="getList" />
    </div>
    <div slot="search">
      <el-form v-show="showSearch" ref="queryForm" :model="queryParams" :inline="true" label-width="100px">
        <el-form-item label="排行选择：">
          <el-select v-model="queryParams.type" placeholder="请选择" style="width: 160px" @change="handleQuery">
            <el-option
              v-for="(item, index) in performanceType"
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
    </div>

    <div slot="content">
      <el-table v-loading="loading" border :height="tableHeight" :data="resultList" @selection-change="handleSelectionChange">
        <div slot="empty">
          <el-empty :image="noData" :image-size="200" />
        </div>
        <!-- <el-table-column type="selection" width="55" align="center" /> -->
        <el-table-column label="排行" type="index" align="center" />
        <el-table-column v-if="queryParams.type === 'person'" label="姓名" align="center" prop="userName" />
        <el-table-column
          v-if="queryParams.type === 'deptParent' || queryParams.type === 'dept'"
          label="部门"
          align="center"
          prop="deptName"
        />
        <el-table-column label="当月创收" align="center" prop="monthlyRevenue" />
        <el-table-column label="今日新增创收" align="center" prop="dailyRevenue" />
        <el-table-column label="当日/当月上门" align="center" prop="visitMonthlyCnt">
          <template slot-scope="scope"> {{ scope.row.visitDailyCnt }} / {{ scope.row.visitMonthlyCnt }} </template>
        </el-table-column>
        <el-table-column label="当日/当月进件" align="center" prop="monthlyCnt">
          <template slot-scope="scope"> {{ scope.row.dailyCnt }} / {{ scope.row.monthlyCnt }} </template>
        </el-table-column>
        <el-table-column label="当日/当月收款件数" align="center" prop="monthlyPayCnt">
          <template slot-scope="scope"> {{ scope.row.dailyPayCnt }} / {{ scope.row.monthlyPayCnt }} </template>
        </el-table-column>
        <el-table-column label="当日/当月原始分配数" align="center" prop="flowMonthlyCnt">
          <template slot-scope="scope"> {{ scope.row.flowDailyCnt }} / {{ scope.row.flowMonthlyCnt }} </template>
        </el-table-column>
        <el-table-column label="月批款额度" align="center" prop="monthlyAppro" />
        <el-table-column label="总业绩" align="center" prop="yearRevenue" />
      </el-table>
    </div>
    <div slot="footer">
      <pagination :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize" @pagination="getList" />
    </div>
  </TablePanel>
</template>

<script>
import { result } from "@/api/crm/info";
import noData from "@/assets/img/no_data.png";
import { mapGetters } from "vuex";
export default {
  name: "Result",
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
      // 表格数据
      resultList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        type: "person",
        queryTime: [new Date(new Date().getFullYear(), new Date().getMonth(), 1).setHours(0, 0, 0, 0), new Date().getTime()]
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {},
      performanceType: [
        {
          label: "个人业绩排行",
          value: "person"
        },
        {
          label: "公司业绩排行",
          value: "company"
        },
        {
          label: "一级部门业绩排行",
          value: "deptParent"
        },
        {
          label: "二级部门业绩排行",
          value: "dept"
        }
      ]
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询列表 */
    getList() {
      this.loading = true;
      result(this.queryParams).then((response) => {
        this.resultList = response.rows;
        this.total = response.total;
        this.loading = false;
        this.$nextTick(() => {
          // this.tableHeight = this.getTableHeightWithTitle();
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
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map((item) => item.resultId);
      this.single = selection.length !== 1;
      this.multiple = !selection.length;
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download(
        "crm/info/export",
        {
          ...this.queryParams
        },
        `result_${new Date().getTime()}.xlsx`
      );
    }
  }
};
</script>
