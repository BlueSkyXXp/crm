<template>
  <drawer-view
    :body-style="{ height: '100%'}"
    width="1200px"
    @close="close"
    @afterEnter="afterEnter"
  >
    <flexbox
      v-loading="loading"
      direction="column"
      align="stretch"
      class="st-drawer"
    >
      <flexbox class="st-drawer__header">
        <img :src="customer_active" class="header-mark">
        <div class="title">{{ params.customerName }}</div>
        <div>
          <el-button type="primary">保 存</el-button>
          <el-button type="primary">关 闭</el-button>
        </div>
      </flexbox>
      <div class="st-drawer__body">
        <st-sections title="基本信息">
          <el-descriptions :column="3" border style="margin-top: 10px;">
            <el-descriptions-item label="客户来源:">{{ params.sourceDisp }}</el-descriptions-item>
            <el-descriptions-item label="客户性质:">{{ params.natureDisp }}</el-descriptions-item>
            <el-descriptions-item label="客户行业:">{{ params.industryDisp }}</el-descriptions-item>
            <el-descriptions-item label="客户分级:">
              <el-tag size="small">{{ params.gradeDisp }}</el-tag>
            </el-descriptions-item>
            <el-descriptions-item label="详细地址:">{{ params.address }}</el-descriptions-item>
          </el-descriptions>
        </st-sections>
        <st-sections title="关联信息">
          <el-tabs type="border-card" style="margin-top: 10px;">
            <el-tab-pane label="跟进记录" name="1">跟进记录</el-tab-pane>
            <el-tab-pane label="详细信息" name="2">详细信息</el-tab-pane>
            <el-tab-pane label="相关联系人" name="3">相关联系人</el-tab-pane>
            <el-tab-pane label="商机" name="4">商机</el-tab-pane>
            <el-tab-pane label="报价单" name="5">报价单</el-tab-pane>
            <el-tab-pane label="合同" name="6">合同</el-tab-pane>
            <el-tab-pane label="回款计划" name="7">回款计划</el-tab-pane>
            <el-tab-pane label="回款单" name="8">回款单</el-tab-pane>
            <el-tab-pane label="操作记录" name="9">操作记录</el-tab-pane>
          </el-tabs>
        </st-sections>
      </div>
    </flexbox>
  </drawer-view>
</template>

<script>
import DrawerView from "@/components/StDrawer/DrawerView.vue";
import flexbox from "@/components/Flexbox/Flexbox.vue";
import customer_active from "@/assets/img/address_logo.png";
export default {
  // 创建页面
  name: "XrCreate",
  components: {
    DrawerView,
    flexbox
  },
  props: {
    title: { type: String,
      default: ""
    },
    loading: Boolean,
    appendToBody: {
      type: Boolean,
      default: true
    },
    showCancel: {
      type: Boolean,
      default: true
    },
    showConfirm: {
      type: Boolean,
      default: true
    },
    confirmDisabled: {
      type: Boolean,
      default: false
    },
    confirmButtonText: {
      type: String,
      default: "保存"
    }
  },
  data() {
    return {
      customer_active: customer_active,
      params: {}
    };
  },
  computed: {},
  watch: {},
  created() {},
  mounted() {
    if (this.appendToBody) {
      document.body.appendChild(this.$el);
    }
  },

  destroyed() {
    if (this.appendToBody) {
      if (this.$el && this.$el.parentNode) {
        this.$el.parentNode.removeChild(this.$el);
      }
    }
  },
  methods: {
    show(param) {
      this.params = Object.assign("", param);
      console.log(this.params);
    },

    afterEnter() {
      this.$emit("afterEnter");
    },

    save() {
      this.$emit("save");
    },

    close() {
      this.$emit("close");
    }
  }
};
</script>

<style lang="scss" scoped>
.st-drawer {
  position: relative;
  height: 100%;

  &__header {
    height: 40px;
    margin-bottom: 10px;
    padding: 0 10px;
    flex-shrink: 0;
    border-bottom: 1px solid #d3dce6;
    .title {
      flex:1;
      font-size:17px;
      color:#333;
      font-weight: bold;
      margin-left: 10px;
    }
    .close {
      display: block;
      font-size: 24px;
      color: #909399;
      margin-right: -10px;
      padding: 10px;
      cursor: pointer;
    }
    .close:hover {
      color: #909399;
    }
  }

  &__body {
    position: relative;
    overflow-x: hidden;
    overflow-y: auto;
    flex: 1;
  }

  &__footer {
    position: relative;
    text-align: center;
    padding: 10px 20px 0;
    border-top: 1px solid #d3dce6;
  }

  .header-mark{
    //border-left-color: #2362FB;
    //border-left-width: 6px;
    //border-left-style: solid;
    //border-radius: 0px;
    height: 30px;
    width: 30px;
    //padding-right: 9px;
  }
}
</style>
