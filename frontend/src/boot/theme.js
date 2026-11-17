import { boot } from 'quasar/wrappers'
import { Dark } from 'quasar'

const STORAGE_KEY = 'tms-theme'
const MODES = ['light', 'dark', 'auto']

export function getTmsTheme() {
  return localStorage.getItem(STORAGE_KEY) || 'auto'
}

export function setTmsTheme(mode) {
  const selectedMode = MODES.includes(mode) ? mode : 'auto'

  localStorage.setItem(STORAGE_KEY, selectedMode)

  Dark.set(selectedMode === 'auto' ? 'auto' : selectedMode === 'dark')
}

export default boot(() => {
  setTmsTheme(getTmsTheme())
})
