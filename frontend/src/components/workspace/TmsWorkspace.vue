/** Główny komponent workspace dla danego modułu. Implementuje podział na panele, zakładki oraz
akcje szczegółowe */

<template lang="pug">
.tms-workspace.column.no-wrap
  .workspace-heading(v-if="showHeading")
    .text-h5.text-weight-bold {{ title }}
    .text-caption {{ description }}

  .workspace-main.col
    q-splitter.workspace-splitter(
      v-model="splitterValue"
      unit="%"
      :limits="[minSplit, maxSplit]"
      :disable="!detailsVisible"
      :class="{ 'workspace-splitter--collapsed': !detailsVisible }"
    )
      //- LEWY PANEL
      template(#before)
        .workspace-panel.column.no-wrap
          .workspace-toolbar.row.items-center.no-wrap

            //- szukajka
            TmsWorkspaceSearch(
              v-model="searchValue"
              :placeholder="searchPlaceholder"
            )

            //- Szukajka & lista buttonów
            TmsWorkspaceActions(
              :actions="actions"
              direction="horizontal"
            )

            q-space

            q-btn(
              v-if="!detailsVisible"
              flat
              round
              dense
              icon="view_sidebar"
              aria-label="Pokaż szczegóły"
              @click="toggleDetails"
            )
              q-tooltip Pokaż szczegóły

          .workspace-filters
            slot(name="filters")

          .workspace-list.col
            slot(
              name="list"
              :search="searchValue"
            )

      //- SEPARATOR
      template(#separator)
        .workspace-separator
          q-btn.workspace-collapse-btn(
            v-if="detailsVisible"
            flat
            round
            dense
            icon="chevron_right"
            aria-label="Ukryj szczegóły"
            @click.stop="toggleDetails"
          )
            q-tooltip Ukryj szczegóły

      //- PRAWY PANEL
      template(#after)
        .workspace-panel.column.no-wrap
          .workspace-details-header
            slot(name="details-header")

          TmsWorkspaceTabs(
            :tabs="tabs"
            v-model="activeTab"
          )

          .workspace-details.row.no-wrap.col
            TmsWorkspaceActions.workspace-action-rail(
              :actions="currentDetailActions"
              direction="vertical"
            )

            .workspace-detail-content.col
              slot(
                name="detail-content"
                :active-tab="activeTab"
              )
</template>

<script setup>
import { computed, ref, watch } from 'vue'
import TmsWorkspaceActions from './TmsWorkspaceActions.vue'
import TmsWorkspaceSearch from './TmsWorkspaceSearch.vue'
import TmsWorkspaceTabs from './TmsWorkspaceTabs.vue'

const props = defineProps({
  moduleId: { type: String, required: true },
  title: { type: String, default: '' },
  description: { type: String, default: '' },
  showHeading: { type: Boolean, default: false },
  searchPlaceholder: { type: String, default: 'Szukaj...' },

  actions: { type: Array, default: () => [] },
  tabs: { type: Array, default: () => [] },
  detailActions: { type: Object, default: () => ({}) },

  splitter: { type: Number, default: 42 },
  minSplit: { type: Number, default: 25 },
  maxSplit: { type: Number, default: 75 },
  showDetails: { type: Boolean, default: true },
})

const emit = defineEmits(['action', 'detail-action', 'search'])

const storageKey = `tms.workspace.${props.moduleId}`

function readStorage(key, fallback) {
  try {
    return localStorage.getItem(key) ?? fallback
  } catch {
    return fallback
  }
}

function writeStorage(key, value) {
  try {
    localStorage.setItem(key, String(value))
  } catch {
    // localStorage może być niedostępny.
  }
}

const savedSplit = Number(readStorage(`${storageKey}.split`, props.splitter))

const splitterValue = ref(
  Number.isFinite(savedSplit)
    ? Math.max(props.minSplit, Math.min(props.maxSplit, savedSplit))
    : props.splitter,
)

const detailsVisible = ref(
  readStorage(`${storageKey}.visible`, String(props.showDetails)) === 'true',
)

const searchValue = ref('')
const activeTab = ref(props.tabs[0]?.name ?? '')

const currentDetailActions = computed(() => {
  return props.detailActions[activeTab.value] ?? []
})

watch(splitterValue, (value) => {
  writeStorage(`${storageKey}.split`, value)
})

watch(detailsVisible, (value) => {
  writeStorage(`${storageKey}.visible`, value)
})

watch(searchValue, (value) => {
  emit('search', value ?? '')
})

function toggleDetails() {
  detailsVisible.value = !detailsVisible.value
}
</script>

<style scoped lang="scss">
.tms-workspace {
  height: 100%;
  min-height: 0;
  overflow: hidden;
}

.workspace-heading {
  flex-shrink: 0;
  padding: 16px 20px;
}

.workspace-main {
  min-height: 0;
  overflow: hidden;
}

.workspace-splitter {
  height: 100%;
  min-height: 0;
}

//- Ukrycie prawego panelu bez odmontowywania listy
.workspace-splitter--collapsed {
  :deep(.q-splitter__before) {
    width: 100% !important;
    flex: 0 0 100% !important;
  }

  :deep(.q-splitter__after),
  :deep(.q-splitter__separator) {
    display: none !important;
  }
}

.workspace-panel {
  height: 100%;
  min-width: 0;
  overflow: hidden;
}

.workspace-toolbar {
  flex-shrink: 0;
  min-width: 0;
  padding: 8px 10px;
  gap: 8px;
  border-bottom: 1px solid var(--q-separator-color);
}

.workspace-search {
  flex: 0 1 300px;
  min-width: 160px;
}

.workspace-toolbar :deep(.tms-workspace-actions--horizontal) {
  flex: 1 1 auto;
  min-width: 0;
  overflow-x: auto;
  overflow-y: hidden;
}

.workspace-filters {
  flex-shrink: 0;
}

.workspace-list,
.workspace-detail-content {
  min-height: 0;
  min-width: 0;
  overflow: auto;
}

.workspace-details-header {
  flex-shrink: 0;
}

.workspace-tabs {
  flex-shrink: 0;
  border-bottom: 1px solid var(--q-separator-color);
}

.workspace-details {
  min-height: 0;
}

.workspace-action-rail {
  flex-shrink: 0;
  width: 52px;
  border-right: 1px solid var(--q-separator-color);
}

.workspace-separator {
  height: 100%;
  display: flex;
  align-items: flex-start;
  justify-content: center;
  cursor: col-resize;
}

.workspace-collapse-btn {
  margin-top: 8px;
}
</style>
