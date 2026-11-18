<template lang="pug">
q-page
  TmsWorkspace(
    module-id="kontrahenci"
    search-placeholder="Szukaj kod, nazwa..."
    :actions="actions"
    :tabs="tabs"
    :detail-actions="detailActions"
    :splitter="42"
    @action="handleAction"
    @detail-action="handleDetailAction"
    @search="handleSearch")
  //- .text-h5.q-mb-md Kontrahenci
  //- q-btn(color="primary" label="Pobierz kontrahentów" :loading="loading" @click="loadKontrahenci")
  //- q-banner.bg-red-1.q-mt-md(v-if="error") {{ error }}
  //- q-table.q-mt-md(title="Lista kontrahentów" :rows="kontrahenci" :columns="columns" row-key="id" :loading="loading")
</template>

<script setup>
import TmsWorkspace from '../../components/workspace/TmsWorkspace.vue'

const actions = [
  { name: 'add', label: 'Dodaj', icon: 'add' },
  { name: 'edit', label: 'Edytuj', icon: 'edit', disabled: true },
  { name: 'clone', label: 'Klonuj', icon: 'content_copy', disabled: true },
  { name: 'delete', label: 'Usuń', icon: 'delete', color: 'negative', disabled: true },
  { name: 'filter', label: 'Filtry', icon: 'filter_alt' },
  { name: 'report', label: 'Raport', icon: 'bar_chart' },
]

const tabs = [
  { name: 'profile', label: 'Profil', icon: 'business' },
  { name: 'contacts', label: 'Osoby kontaktowe', icon: 'people' },
  { name: 'addresses', label: 'Adresy', icon: 'place' },
  { name: 'invoices', label: 'Faktury', icon: 'receipt_long' },
  { name: 'contracts', label: 'Umowy', icon: 'description' },
  { name: 'documents', label: 'Dokumenty', icon: 'folder' },
  { name: 'notes', label: 'Uwagi', icon: 'chat' },
  { name: 'history', label: 'Historia', icon: 'history' },
]

const detailActions = {
  profile: [{ name: 'edit', label: 'Edytuj profil', icon: 'edit' }],
  contacts: [
    { name: 'add', label: 'Dodaj osobę', icon: 'person_add' },
    { name: 'edit', label: 'Edytuj osobę', icon: 'edit' },
    { name: 'delete', label: 'Usuń osobę', icon: 'delete' },
  ],
  documents: [
    { name: 'upload', label: 'Dodaj dokument', icon: 'upload_file' },
    { name: 'download', label: 'Pobierz dokument', icon: 'download' },
  ],
}

function handleAction(action) {
  console.log('Akcja modułu:', action)
}

function handleDetailAction(payload) {
  console.log('Akcja szczegółów:', payload.tab, payload.action)
}

function handleSearch(value) {
  console.log('Wyszukiwanie kontrahentów:', value)
}
</script>

<style scoped lang="scss"></style>
