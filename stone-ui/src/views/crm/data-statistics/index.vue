<template>
  <TablePanel title="日内部数据统计">
    <div slot="btm">
      <right-toolbar :show-search.sync="showSearch" @queryTable="getList" />
    </div>
    <div slot="search">
      <el-form v-show="showSearch" ref="queryForm" :model="queryParams" :inline="true" label-width="100px">
        <el-form-item label="部门名称：" prop="deptName">
          <el-input
            v-model="queryParams.deptName"
            placeholder="请输入部门名称"
            clearable
            @keyup.enter.native="handleQuery"
          />
        </el-form-item>
        <el-form-item>
          <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
          <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
        </el-form-item>
      </el-form>
    </div>

    <div slot="content">
      <el-table
        v-loading="loading"
        border
        :height="tableHeight"
        :data="contractList"
        :summary-method="getSummaries"
        show-summary="true"
        @selection-change="handleSelectionChange"
      >
        <div slot="empty">
          <el-empty :image="noData" :image-size="200" />
        </div>
        <el-table-column type="selection" width="55" align="center" />
        <el-table-column label="部门名称" align="center" prop="deptName" />
        <el-table-column label="用户名" align="center" prop="userName" />
        <el-table-column label="0星数据" align="center" prop="level0Cnt" />
        <el-table-column label="0星比例" align="center" prop="level0Percentage" />
        <el-table-column label="1星数据" align="center" prop="level1Cnt" />
        <el-table-column label="1星比例" align="center" prop="level1Percentage" />
        <el-table-column label="2星数据" align="center" prop="level2Cnt" />
        <el-table-column label="2星比例" align="center" prop="level2Percentage" />
        <el-table-column label="3星数据" align="center" prop="level3Cnt" />
        <el-table-column label="3星比例" align="center" prop="level3Percentage" />
        <el-table-column label="4星数据" align="center" prop="level4Cnt" />
        <el-table-column label="4星比例" align="center" prop="level4Percentage" />
        <el-table-column label="重要客户" align="center" prop="level5Cnt" />
        <el-table-column label="重要客户比例" align="center" prop="level5Percentage" />
        <el-table-column label="客户总数" align="center" prop="cnt" />
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
      <!-- 添加或修改合同对话框 -->
      <st-dialog
        v-if="open"
        :title="title"
        padding="100px 0 250px"
        append-to-body
        @close="cancel"
        @save="submitForm"
      >
        <el-form ref="form" :model="form" :rules="rules" label-width="120px">
          <el-form-item label="合同名称:" prop="contractName">
            <el-input v-model="form.contractName" placeholder="请输入合同名称" />
          </el-form-item>
          <el-form-item label="合同金额:" prop="money">
            <el-input v-model="form.money" placeholder="请输入合同金额" />
          </el-form-item>
          <el-form-item label="客户:" prop="customerId">
            <el-input v-model="form.customerId" placeholder="请输入客户" />
          </el-form-item>
          <el-form-item label="联系人:" prop="contactsId">
            <el-input v-model="form.contactsId" placeholder="请输入联系人" />
          </el-form-item>
          <el-form-item label="商机:" prop="businessId">
            <el-input v-model="form.businessId" placeholder="请输入商机" />
          </el-form-item>
          <el-form-item label="合同类型:" prop="types">
            <el-input v-model="form.types" placeholder="请输入合同类型" />
          </el-form-item>
          <el-form-item label="下单日期:" prop="orderDate">
            <el-input v-model="form.orderDate" placeholder="请输入下单日期" />
          </el-form-item>
          <el-form-item label="开始时间:" prop="startTime">
            <el-input v-model="form.startTime" placeholder="请输入开始时间" />
          </el-form-item>
          <el-form-item label="结束时间:" prop="endTime">
            <el-input v-model="form.endTime" placeholder="请输入结束时间" />
          </el-form-item>
          <el-form-item label="备注：" prop="remark">
            <el-input v-model="form.remark" type="textarea" placeholder="请输入内容" />
          </el-form-item>
        </el-form>
      </st-dialog>
    </div>
    <div slot="footer">
      <pagination
        :total="total"
        :page.sync="queryParams.pageNum"
        :limit.sync="queryParams.pageSize"
        @pagination="getList"
      />
    </div>

  </TablePanel>
</template>

<script>
import { statistics } from "@/api/crm/manage";
import noData from "@/assets/img/no_data.png";
import { mapGetters } from "vuex";
export default {
  name: "Contract",
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
      // 合同表格数据
      contractList: [],
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
      rules: {
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询合同列表 */
    getList() {
      this.loading = true;
      statistics(this.queryParams).then(response => {
        this.contractList = response.rows;
        this.total = response.total;
        this.loading = false;
        this.$nextTick(() => {
          this.tableHeight = this.getTableHeightWithTitle();
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
      this.ids = selection.map(item => item.contractId);
      this.single = selection.length !== 1;
      this.multiple = !selection.length;
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download("crm/contract/export", {
        ...this.queryParams
      }, `contract_${new Date().getTime()}.xlsx`);
    },

    getSummaries(param) {
      const { columns, data } = param
      const len = columns.length
      const sums = []
      columns.forEach((column, index) => {
        //如果是第一列，则最后一行展示为“总计”两个字
        if (index === 0) {
          sums[index] = '总计'
          //如果是最后一列，索引为列数-1，则显示计算总和
        } else if (index % 2 === 1) {
          const values = data.map(item => Number(item[column.property]))
          if (!values.every(value => isNaN(value))) {
            sums[index] = values.reduce((prev, curr) => {
              const value = Number(curr)
              if (!isNaN(value)) {
                return prev + curr
              } else {
                return prev
              }
            }, 0)
          }
        } else {
          sums[index] = ''
        }
      })
      return sums
    }
  }
};
</script>
