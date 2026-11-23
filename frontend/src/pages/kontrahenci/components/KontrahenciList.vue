<template lang="pug">
.column.no-wrap.full-height
  q-table.col(
    title="Lista kontrahentów"
    :rows="kontrahenci"
    :columns="columns"
    row-key="id"
    :loading="loading"
    :pagination="pagination"
    :rows-per-page-options="[10, 20, 50, 100]"
    binary-state-sort
    flat
    dense
    virtual-scroll
    @request="emit('request', $event)"
    @row-click="selectRow"
  )
    template(#body-cell-status="props")
      q-td(:props="props")
        q-badge(
          :color="props.value === 1 ? 'positive' : 'grey'"
        ) {{ props.value === 1 ? 'Aktywny' : 'Nieaktywny' }}

    template(#no-data)
      .full-width.text-center.q-pa-md.text-grey
        | Brak kontrahentów do wyświetlenia
</template>

<script setup>
defineProps({
  kontrahenci: {
    type: Array,
    default: () => [],
  },
  loading: {
    type: Boolean,
    default: false,
  },
  pagination: {
    type: Object,
    required: true,
  },
})

const emit = defineEmits(['select', 'request'])

const columns = [
  {
    name: 'kod',
    label: 'Kod',
    field: 'kod',
    align: 'left',
    sortable: true,
  },
  {
    name: 'nazwa',
    label: 'Nazwa',
    field: 'nazwa',
    align: 'left',
    sortable: true,
  },
  {
    name: 'nip',
    label: 'NIP',
    field: 'nip',
    align: 'left',
    sortable: true,
  },
  {
    name: 'miasto',
    label: 'Miasto',
    field: 'adresMiasto',
    align: 'left',
    sortable: true,
  },
  {
    name: 'telefon',
    label: 'Telefon',
    field: 'telefon',
    align: 'left',
  },
  {
    name: 'email',
    label: 'E-mail',
    field: 'email',
    align: 'left',
  },
  {
    name: 'status',
    label: 'Status',
    field: 'status',
    align: 'center',
    sortable: true,
  },
]

function selectRow(event, row) {
  emit('select', row)
}
</script>
