import { ref } from 'vue'
import { kontrahentService } from '../services/kontrahentService'

export function useKontrahent() {
  const kontrahenci = ref([])
  const loading = ref(false)
  const error = ref(null)

  const pagination = ref({
    page: 1,
    rowsPerPage: 20,
    rowsNumber: 0,
    sortBy: 'kod',
    descending: false,
  })

  let requestId = 0

  async function loadKntList() {
    const currentRequest = ++requestId

    loading.value = true
    error.value = null

    try {
      const response = await kontrahentService.getAll({
        page: pagination.value.page - 1,
        size: pagination.value.rowsPerPage,
        sort: `${pagination.value.sortBy || 'kod'},${pagination.value.descending ? 'desc' : 'asc'}`,
      })

      if (currentRequest !== requestId) return

      kontrahenci.value = response.data.content
      pagination.value.rowsNumber = response.data.totalElements
    } catch (err) {
      if (currentRequest !== requestId) return

      error.value = err.message
      console.error('Błąd pobierania kontrahentów:', err)
    } finally {
      if (currentRequest === requestId) {
        loading.value = false
      }
    }
  }

  async function refreshKntList() {
    await loadKntList()
  }

  async function changePagination(newPagination) {
    pagination.value = {
      ...pagination.value,
      ...newPagination,
    }

    await loadKntList()
  }

  return {
    kontrahenci,
    loading,
    error,
    pagination,
    loadKntList,
    refreshKntList,
    changePagination,
  }
}
