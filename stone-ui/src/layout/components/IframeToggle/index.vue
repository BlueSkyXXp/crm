<template>
  <transition-group name="fade-transform" mode="out-in">
    <inner-link
      v-for="(item, index) in iframeViews"
      v-show="$route.path === item.path"
      :key="item.path"
      :iframe-id="'iframe' + index"
      :src="iframeUrl(item.meta.link, item.query)"
    />
  </transition-group>
</template>

<script>
import InnerLink from "../InnerLink/index";

export default {
  components: { InnerLink },
  computed: {
    iframeViews() {
      return this.$store.state.tagsView.iframeViews;
    }
  },
  methods: {
    iframeUrl(url, query) {
      if (Object.keys(query).length > 0) {
        let params = Object.keys(query).map((key) => key + "=" + query[key]).join("&");
        return url + "?" + params;
      }
      return url;
    }
  }
};
</script>
