/** * Komponent paska bocznego TMS. Zawiera nawigację główną oraz stopkę z informacjami o wersji i
statusie API. */

<template lang="pug">
.tms-sidebar-inner
  nav.tms-nav(aria-label="Nawigacja główna")
    template(v-for="(item) in items" :key="item.to")
      q-separator.tms-nav-divider(v-if="item.to === '/ustawienia'")

      q-item.tms-nav-item(
        clickable
        :to="item.to"
        exact
        active-class="tms-nav-active"
        @click="navigate"
      )
        q-item-section(avatar)
          component(
            :is="item.icon"
            :size="23"
            :stroke="1.75"
          )
        q-item-section {{ item.label }}

  .tms-sidebar-footer
    span v1.0.0
    span.tms-api-status
      i
      | API: status niezweryfikowany
</template>

<script setup>
import {
  IconHome,
  IconClipboardList,
  IconTruckDelivery,
  IconUsersGroup,
  IconUserSquare,
  IconCarGarage,
  IconFiles,
  IconReceipt2,
  IconChartBar,
  IconSettings,
} from '@tabler/icons-vue'

const emit = defineEmits(['navigate'])

const items = [
  {
    label: 'Dashboard',
    icon: IconHome,
    to: '/dashboard',
    enabled: true,
  },
  {
    label: 'Zlecenia transportowe',
    icon: IconClipboardList,
    to: '/zlecenia',
  },
  {
    label: 'Transporty',
    icon: IconTruckDelivery,
    to: '/transporty',
  },
  {
    label: 'Kontrahenci',
    icon: IconUsersGroup,
    to: '/kontrahenci',
  },
  {
    label: 'Kierowcy',
    icon: IconUserSquare,
    to: '/kierowcy',
  },
  {
    label: 'Flota',
    icon: IconCarGarage,
    to: '/flota',
  },
  {
    label: 'Dokumenty',
    icon: IconFiles,
    to: '/dokumenty',
  },
  {
    label: 'Faktury',
    icon: IconReceipt2,
    to: '/faktury',
  },
  {
    label: 'Raporty',
    icon: IconChartBar,
    to: '/raporty',
  },
  {
    label: 'Ustawienia',
    icon: IconSettings,
    to: '/ustawienia',
  },
]

function navigate() {
  emit('navigate')
}
</script>

<style scoped lang="scss">
.tms-sidebar-inner {
  height: 100%;
  display: flex;
  flex-direction: column;
  background: var(--tms-sidebar);
  color: var(--tms-text);
}

// Nawigacja

.tms-nav {
  padding: 24px 9px 10px;
  flex: 1;
}

.tms-nav-item {
  min-height: 46px;
  border-radius: 8px;
  margin: 2px 0;
  color: var(--tms-text);
  font-size: 14px;
  border: 1px solid transparent;
  transition:
    background-color 0.15s ease,
    color 0.15s ease,
    border-color 0.15s ease;

  :deep(.q-item__section--avatar) {
    min-width: 42px;
  }

  // Ikony Tabler
  svg {
    display: block;
    flex-shrink: 0;
    color: inherit;
  }

  &:hover {
    background: var(--tms-nav-hover-bg);
  }

  &.tms-nav-active {
    background: var(--tms-nav-active-bg) !important;
    color: var(--tms-nav-active-text) !important;
    font-weight: 600;
    border-color: var(--tms-primary);

    svg {
      color: inherit;
    }
  }
}

// Separator sekcji

.tms-nav-divider {
  margin: 15px 9px 12px;
  background: var(--tms-border);
}

// Stopka sidebara

.tms-sidebar-footer {
  padding: 17px 20px;
  border-top: 1px solid var(--tms-border);

  display: flex;
  align-items: center;
  justify-content: space-between;
  gap: 6px;

  font-size: 11px;
  color: var(--tms-text-muted);
}

// Status API

.tms-api-status {
  display: flex;
  align-items: center;
  gap: 6px;
  white-space: nowrap;

  i {
    display: block;
    width: 8px;
    height: 8px;
    flex-shrink: 0;
    border-radius: 50%;
    background: var(--tms-text-muted);
  }
}
</style>
