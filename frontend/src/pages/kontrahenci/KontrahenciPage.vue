<template lang="pug">
q-page
  TmsWorkspace(
    module-id="kontrahenci"
    search-placeholder="Szukaj kod, nazwa..."
    :actions="actions"
    :tabs="tabs"
    :detail-actions="detailActions"
    :splitter="42"
    @search="handleSearch"
    )
    //-  LEWY PANEL - LISTA KONTRAHENTÓW
    template(#list)
      .column.no-wrap.full-height
        q-banner.bg-red-1.text-negative(
          v-if="error"
          dense
        ) {{ error }}
      KontrahenciList(
        :kontrahenci="kontrahenci"
        @select="selectKontrahent"
      )

    //- PRAWY PANEL - SZCZEGÓŁY KONTRAHENTA

    template(#detail-content="{ activeTab }")
      .q-pa-md(v-if="selectedKontrahent")
        template(v-if="activeTab === 'profile'")
          .text-h6 {{ selectedKontrahent.nazwa }}
          q-separator.q-my-md

          div Kod: {{ selectedKontrahent.kod }}
          div NIP: {{ selectedKontrahent.nip }}
          div REGON: {{ selectedKontrahent.regon }}
          div Miasto: {{ selectedKontrahent.adresMiasto }}
          div Telefon: {{ selectedKontrahent.telefon }}
          div E-mail: {{ selectedKontrahent.email }}

        template(v-else)
          .text-grey
            | Zakładka w przygotowaniu: {{ activeTab }}

      .q-pa-md.text-grey(v-else)
        | Wybierz kontrahenta z listy.
</template>

<script setup>
import { ref } from 'vue'
import { useKontrahent } from '../../composables/useKontrahent.js'
import TmsWorkspace from '../../components/workspace/TmsWorkspace.vue'
import KontrahenciList from './components/KontrahenciList.vue'
// import KontrahenciDetails from './KontrahenciDetails.vue'

const { kontrahenci, error, loadKntList, refreshKntList } = useKontrahent()
const selectedKontrahent = ref(null)

const actions = [
  {
    name: 'add',
    label: 'Dodaj',
    icon: 'add',
    click: () => console.log('%c Dodawanie kontrahenta', 'color: green; font-size: 18px'),
  },
  { name: 'edit', label: 'Edytuj', icon: 'edit', disabled: true },
  { name: 'clone', label: 'Klonuj', icon: 'content_copy', disabled: true },
  { name: 'delete', label: 'Usuń', icon: 'delete', color: 'negative', disabled: true },
  { name: 'filter', label: 'Filtry', icon: 'filter_alt' },
  { name: 'report', label: 'Raport', icon: 'bar_chart' },
  {
    name: 'refresh',
    label: 'Pobierz listę kontrahentów refreshem',
    icon: 'refresh',
    click: refreshKntList,
  },
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
  profile: [
    {
      name: 'edit',
      label: 'Edytuj profil',
      icon: 'edit',
      click: () => editKontrahent(selectedKontrahent.value),
    },
  ],
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

function selectKontrahent(kontrahent) {
  selectedKontrahent.value = kontrahent
}

function handleSearch(value) {
  console.log('Wyszukiwanie kontrahentów:', value)
  loadKntList(value)
}

function editKontrahent(kontrahent) {
  console.log('Edytowanie kontrahenta:', kontrahent)
}
</script>

<style scoped lang="scss"></style>
