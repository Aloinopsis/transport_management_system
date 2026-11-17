<template lang="pug">
q-layout.tms-layout(view="hHh Lpr fFf")
  q-header.tms-header
    TmsHeader(@toggle-drawer="toggleDrawer")

  q-drawer.tms-drawer(
    :model-value="isDesktop || drawerOpen"
    @update:model-value="onDrawerChange"
    :breakpoint="1100"
    :width="250"
    bordered
  )
    TmsSidebar(@navigate="onNavigate")

  q-page-container.tms-page-container
    router-view
</template>

<script setup>
import { ref, computed } from 'vue'
import { useQuasar } from 'quasar'
import TmsHeader from '../components/layout/TmsHeader.vue'
import TmsSidebar from '../components/layout/TmsSidebar.vue'

const $q = useQuasar()

const drawerOpen = ref(false)

const isDesktop = computed(() => $q.screen.width >= 1100)

function toggleDrawer() {
  if (!isDesktop.value) {
    drawerOpen.value = !drawerOpen.value
  }
}

function onDrawerChange(value) {
  if (!isDesktop.value) {
    drawerOpen.value = value
  }
}

function onNavigate() {
  if (!isDesktop.value) {
    drawerOpen.value = false
  }
}
</script>

<style scoped lang="scss">
.tms-layout {
  min-height: 100vh;
  background: var(--tms-bg);
  color: var(--tms-text);
}

.tms-page-container {
  min-height: 100vh;
  background: var(--tms-bg);
  color: var(--tms-text);
}

.tms-header {
  background: var(--tms-header) !important;
  color: var(--tms-text) !important;
  border-bottom: 1px solid var(--tms-border);
  box-shadow: none !important;
}

.tms-drawer {
  background: var(--tms-sidebar) !important;
  color: var(--tms-text) !important;
  border-right: 1px solid var(--tms-border) !important;

  :deep(.q-drawer) {
    background: var(--tms-sidebar);
  }

  :deep(.q-drawer__content) {
    background: var(--tms-sidebar);
  }
}
</style>
