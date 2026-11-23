/** Komponent wyświetlający zakładki w workspace dla danego modułu */

<template lang="pug">
  .tms-workspace-tabs
    q-tabs.workspace-tabs(
      v-model="activeTab"
      dense
      no-caps
      inline-label
      outside-arrows
      mobile-arrows
      align="left"
      active-color="primary"
      indicator-color="primary"
    )
      q-tab(
        v-for="tab in tabs"
        :key="tab.name"
        :name="tab.name"
        :icon="tab.icon"
        :label="tab.label"
      )
</template>

<script setup>
defineProps({
  tabs: {
    type: Array,
    default: () => [],
  },
})

const activeTab = defineModel({
  type: String,
  default: '',
})
</script>
