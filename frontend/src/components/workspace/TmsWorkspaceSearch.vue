<template lang="pug">
  q-input.workspace-search(
    v-model="searchValue"
    dense
    outlined
    clearable
    :debounce="350"
    :placeholder="placeholder"
  )
    template(#prepend)
      q-icon(name="search")
</template>

<script setup>
defineProps({
  placeholder: {
    type: String,
    default: 'Szukaj...',
  },
})

const searchValue = defineModel({
  type: String,
  default: '',
})
</script>

<style lang="css"></style>
