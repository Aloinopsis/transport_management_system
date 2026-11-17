/** * Komponent nagłówka TMS. Zawiera logo, pasek wyszukiwania, przyciski powiadomień, pomocy i
użytkownika. */

<template lang="pug">
.tms-header-inner
  // Przycisk menu mobilnego
  q-btn.tms-mobile-menu(
    flat
    round
    dense
    icon="menu"
    aria-label="Otwórz menu"
    @click="emit('toggle-drawer')"
  )

  // Logo aplikacji, link do strony głównej
  router-link.tms-brand(to="/dashboard" aria-label="TMS — strona główna")
    IconTruckDelivery(size="48px" color="var(--tms-primary)")
    span.tms-brand-copy
      strong TMS
      small Transport Management System

  // Pasek wyszukiwania
  .tms-search-wrap
    q-input.tms-search(
      ref="searchInput"
      v-model="search"
      dense
      outlined
      placeholder="Szukaj w systemie (zlecenia, transporty, kontrahenci...)"
      aria-label="Wyszukiwanie w systemie"
      @keyup.enter="notReady('Wyszukiwanie globalne')"
    )
      template(#prepend)
        q-icon(name="search")
      template(#append)
        kbd.tms-shortcut Ctrl + K

    // Przycisk powiadomień
  .tms-header-actions
    q-btn(
      flat
      round
      icon="notifications_none"
      aria-label="Powiadomienia"
      @click="notReady('Powiadomienia')"
    )
      // Kropka powiadomień
      span.tms-notification-dot

    // Przycisk pomocy
    q-btn(
      flat
      round
      icon="help_outline"
      aria-label="Pomoc"
      @click="notReady('Pomoc')"
    )

    .tms-header-separator

    // Przycisk użytkownika
    q-btn.tms-user-button(flat no-caps)
      q-avatar.tms-avatar(size="40px") {{ initials }}
      span.tms-user-info
        strong {{ userName }}
        small {{ userRole }}
      q-icon(name="keyboard_arrow_down" size="20px")

      q-menu.tms-user-menu(anchor="bottom right" self="top right")
        q-list(style="min-width: 200px")
          q-item-label(header) Motyw aplikacji
          q-item(
            v-for="option in themeOptions"
            :key="option.value"
            clickable
            v-close-popup
            @click="changeTheme(option.value)"
          )
            q-item-section(avatar)
              q-icon(:name="option.icon")
            q-item-section {{ option.label }}
            q-item-section(v-if="theme === option.value" side)
              q-icon(name="check" color="primary")
          q-separator
          q-item(clickable v-close-popup @click="notReady('Profil użytkownika')")
            q-item-section Profil użytkownika
</template>

<script setup>
import { computed, onMounted, onUnmounted, ref } from 'vue'
import { useQuasar } from 'quasar'
import { getTmsTheme, setTmsTheme } from '../../boot/theme'
import { IconTruckDelivery } from '@tabler/icons-vue'

const emit = defineEmits(['toggle-drawer'])
const $q = useQuasar()
const search = ref('')
const searchInput = ref(null)
const theme = ref(getTmsTheme())

// Dane demonstracyjne do czasu wdrożenia uwierzytelniania
const userName = 'Anna Kowalska'
const userRole = 'Spedytor'
const initials = computed(() =>
  userName
    .split(' ')
    .map((part) => part[0])
    .join('')
    .slice(0, 2),
)

const themeOptions = [
  { label: 'Jasny', value: 'light', icon: 'light_mode' },
  { label: 'Ciemny', value: 'dark', icon: 'dark_mode' },
  { label: 'Automatyczny', value: 'auto', icon: 'brightness_auto' },
]

function onShortcut(event) {
  if ((event.ctrlKey || event.metaKey) && event.key.toLowerCase() === 'k') {
    event.preventDefault()
    searchInput.value?.focus()
  }
}

onMounted(() => window.addEventListener('keydown', onShortcut))
onUnmounted(() => window.removeEventListener('keydown', onShortcut))

function changeTheme(value) {
  theme.value = value
  setTmsTheme(value)
}

function notReady(feature) {
  $q.notify({ type: 'info', message: `${feature} — funkcja w przygotowaniu.` })
}
</script>

<style scoped lang="scss">
.tms-header-inner {
  height: 74px;
  display: flex;
  align-items: center;
  gap: 24px;
  padding: 0 24px;
}

// Logo aplikacji

.tms-brand {
  width: 278px;
  flex: 0 0 278px;
  display: flex;
  align-items: center;
  gap: 16px;
  color: var(--tms-text);
  text-decoration: none;
}

.tms-brand-icon {
  font-size: 42px;
  color: var(--tms-primary);
}

.tms-brand-copy {
  display: flex;
  flex-direction: column;
  line-height: 1.18;
  white-space: nowrap;

  strong {
    font-size: 28px;
    letter-spacing: 0.01em;
  }

  small {
    font-size: 11px;
    margin-top: 5px;
    color: var(--tms-text-muted);
  }
}

// Wyszukiwarka

.tms-search-wrap {
  width: min(650px, 100%);
  flex: 1 1 350px;
  min-width: 0;
}

.tms-search {
  width: 100%;

  :deep(.q-field__control) {
    height: 44px;
    min-height: 44px;
    display: flex;
    align-items: center;
    background: var(--tms-surface-alt);
    border-radius: 10px;
    color: var(--tms-text);

    &::before {
      border-color: var(--tms-border) !important;
    }
  }

  :deep(.q-field__control-container) {
    height: 100%;
    min-width: 0;
    display: flex;
    align-items: center;
  }

  :deep(.q-field__native) {
    height: 100%;
    min-height: 0;
    padding: 0;
    line-height: normal;
    color: var(--tms-text) !important;

    &::placeholder {
      color: var(--tms-text-muted);
      opacity: 1;
    }
  }

  :deep(.q-field__prepend),
  :deep(.q-field__append) {
    height: 100%;
    min-height: 0;
    display: flex;
    align-items: center;
    color: var(--tms-text) !important;

    .q-icon,
    svg {
      flex-shrink: 0;
    }
  }

  :deep(.q-field__bottom) {
    display: none;
  }
}

.tms-shortcut {
  display: inline-flex;
  align-items: center;
  justify-content: center;
  flex-shrink: 0;

  height: 28px;
  padding: 0 7px;

  font-family: inherit;
  font-size: 12px;
  font-weight: 400;
  line-height: 1;

  color: var(--tms-text-muted);
  border: 1px solid var(--tms-border);
  border-radius: 5px;
  white-space: nowrap;
}

// Akcje nagłówka

.tms-header-actions {
  margin-left: auto;
  display: flex;
  align-items: center;
  gap: 14px;
  white-space: nowrap;

  > :deep(.q-btn) {
    color: var(--tms-text);
  }
}

// Powiadomienia

.tms-notification-dot {
  position: absolute;
  right: 8px;
  top: 6px;
  width: 9px;
  height: 9px;
  border-radius: 50%;
  background: var(--tms-warning);
}

.tms-header-separator {
  height: 38px;
  width: 1px;
  flex-shrink: 0;
  background: var(--tms-border);
  margin: 0 5px;
}

// Profil użytkownika

.tms-user-button {
  :deep(.q-btn__content) {
    gap: 12px;
  }
}

.tms-avatar {
  background: var(--tms-primary);
  color: var(--tms-on-primary);
  font-weight: 700;
}

.tms-user-info {
  display: flex;
  flex-direction: column;
  text-align: left;
  gap: 4px;

  strong {
    font-size: 13px;
  }

  small {
    font-size: 12px;
    color: var(--tms-text-muted);
  }
}

// Menu użytkownika
// Klasa może być przeniesiona przez QMenu poza komponent.
// Dlatego styl globalny, ale ograniczony nazwą klasy.

:global(.tms-user-menu) {
  background: var(--tms-surface) !important;
  color: var(--tms-text) !important;
  border: 1px solid var(--tms-border);
}

// Przycisk menu mobilnego

.tms-mobile-menu {
  display: none;
}

// Responsywność

@media (max-width: 1100px) {
  .tms-mobile-menu {
    display: inline-flex;
  }

  .tms-brand {
    width: auto;
    flex: 0 0 auto;
  }

  .tms-brand-copy small {
    display: none;
  }

  .tms-header-inner {
    gap: 14px;
    padding: 0 16px;
  }
}

@media (max-width: 700px) {
  .tms-header-inner {
    height: 64px;
    gap: 10px;
  }

  .tms-brand-copy,
  .tms-user-info,
  .tms-header-separator,
  .tms-shortcut {
    display: none;
  }

  .tms-brand-icon {
    font-size: 31px;
  }

  .tms-search-wrap {
    min-width: 0;
    flex: 1 1 auto;
  }

  .tms-header-actions {
    gap: 2px;

    > :deep(.q-btn:nth-child(2)) {
      display: none;
    }
  }

  .tms-avatar {
    font-size: 12px;
  }
}
</style>
