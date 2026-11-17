<template lang="pug">
  q-page.tms-module-page
    .tms-module-heading
      h1 {{ title }}
      p Moduł jest w przygotowaniu.

    q-card.tms-module-card(flat bordered)
      q-card-section
        .text-subtitle1 {{ title }}
        .tms-muted Funkcjonalność zostanie udostępniona w kolejnych etapach rozwoju TMS.
</template>

<script setup>
import { computed } from 'vue'
import { useRoute } from 'vue-router'

// defineProps({
//   title: {
//     type: String,
//     required: true,
//   },
// })

const route = useRoute()
const title = computed(() => route.meta.title ?? 'Moduł w przygotowaniu')
</script>

<style scoped lang="scss">
.tms-module-page {
  padding: 24px;
  background: var(--tms-bg);
  color: var(--tms-text);
}

.tms-module-heading {
  margin-bottom: 24px;

  h1 {
    margin: 0 0 8px;
    font-size: 24px;
    line-height: 1.4;
    font-weight: 600;
  }

  p {
    margin: 0;
    color: var(--tms-text-muted);
  }
}

.tms-module-card {
  background: var(--tms-surface);
  color: var(--tms-text);
  border-color: var(--tms-border);
  border-radius: 12px;
}
</style>
