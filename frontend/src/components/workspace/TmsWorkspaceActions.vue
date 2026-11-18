/** Komponent wyświetlający listę przycisków akcji w workspace dla danego modułu */

<template lang="pug">
.tms-workspace-actions(
  :class="`tms-workspace-actions--${direction}`"
)
  q-btn(
    v-for="action in visibleActions"
    :key="action.name"
    flat
    round
    dense
    :icon="action.icon"
    :color="action.color || 'primary'"
    :disable="disabled || action.disabled"
    :aria-label="action.label"
    @click="emit('action', action.name)"
  )
    q-tooltip {{ action.label }}
</template>

<script setup>
import { computed } from 'vue'

const props = defineProps({
  actions: {
    type: Array,
    default: () => [],
  },
  direction: {
    type: String,
    default: 'horizontal',
    validator: (value) => ['horizontal', 'vertical'].includes(value),
  },
  disabled: {
    type: Boolean,
    default: false,
  },
})

const emit = defineEmits(['action'])

const visibleActions = computed(() => props.actions.filter((action) => action.visible !== false))
</script>

<style scoped lang="scss">
.tms-workspace-actions {
  display: flex;
  gap: 4px;

  &--horizontal {
    flex-direction: row;
    align-items: center;
    flex-wrap: nowrap;
    overflow-x: auto;
    overflow-y: hidden;
    min-width: 0;
  }

  &--vertical {
    flex-direction: column;
    align-items: center;
    flex-wrap: nowrap;
    overflow-y: auto;
    overflow-x: hidden;
    padding: 6px 3px;
  }

  :deep(.q-btn) {
    flex-shrink: 0;
  }
}
</style>
