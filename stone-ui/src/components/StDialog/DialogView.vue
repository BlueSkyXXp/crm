<template>
  <transition name="opacity-fade" @after-enter="afterEnter">

    <div
      :style="{ 'background-color': backgroundColor, 'padding': padding+' 0', 'z-index': zIndex }"
      class="c-view"
    >
      <el-card
        v-loading="loading"
        :style="{ 'width': width ,'height':height }"
        :body-style="bodyStyle"
        class="crm-drawer-card-container"
      >
        <slot name="header" />
        <slot />
      </el-card>
    </div>
  </transition>
</template>
<script type="text/javascript">
import { getMaxIndex } from "@/utils/index";

export default {
  name: "DrawerView", // 所有新建效果的view
  components: {},
  props: {
    bodyStyle: {
      type: Object,
      default: () => {
        return {};
      }
    },
    loading: {
      type: Boolean,
      default: false
    },
    // 更改背景颜色颜色
    backgroundColor: {
      type: String,
      default: "rgba(0, 0, 0, .4)" // rgba(0, 0, 0, 0.6) 黑色半透明
    },
    /** 展示内容的宽 */
    width: {
      type: String,
      default: "900px"
    },
    /** 展示内容的宽 */
    height: {
      type: String,
      default: "500px"
    },
    /** 展示内容的上下padding */
    padding: {
      type: String,
      default: "40px"
    }
  },
  data() {
    return {
      zIndex: getMaxIndex(),
      loadingObj: null
    };
  },
  computed: {},
  watch: {},
  mounted() {},
  methods: {
    afterEnter() {
      this.$emit("afterEnter");
    },
    close() {
      this.$emit("close");
    }
  }
};
</script>
<style lang="scss" scoped>
.opacity-fade-enter-active,
.opacity-fade-leave-active {
  transition: all 0.5s;
}
.opacity-fade-enter,
.opacity-fade-leave-to {
  opacity: 0;
}
/** 容器布局 */
.c-view {
  position: fixed;
  left: 0;
  top: 0;
  bottom: 0;
  right: 0;
}
.crm-drawer-card-container {
  margin: 0 auto;
  height: 100%;
  ::v-deep .el-card__body {
    padding: 10px;
  }
}
::v-deep .el-card{
  border-radius: 0px !important;
}
</style>
