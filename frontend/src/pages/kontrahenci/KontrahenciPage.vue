<template lang="pug">
q-page(padding)
  .text-h5.q-mb-md Kontrahenci
  q-btn(color="primary" label="Pobierz kontrahentów" :loading="loading" @click="loadKontrahenci")
  q-banner.bg-red-1.q-mt-md(v-if="error") {{ error }}
  q-table.q-mt-md(title="Lista kontrahentów" :rows="kontrahenci" :columns="columns" row-key="id" :loading="loading")
</template>

<script setup>
import { ref } from 'vue'
import { kontrahentService } from '../../services/kontrahentService'

const kontrahenci = ref([])
const loading = ref(false)
const error = ref('')

const columns = [
  { name: 'id', label: 'ID', field: 'id', align: 'left' },
  { name: 'kod', label: 'Kod', field: 'kod', align: 'left' },
  { name: 'nazwa', label: 'Nazwa', field: 'nazwa', align: 'left' },
]

async function loadKontrahenci() {
  loading.value = true
  error.value = ''

  try {
    const response = await kontrahentService.getAll()
    console.log(response.data)
    kontrahenci.value = response.data.content
  } catch (err) {
    console.error(err)
    error.value = 'Nie udało się pobrać kontrahentów.'
  } finally {
    loading.value = false
  }
}
</script>
