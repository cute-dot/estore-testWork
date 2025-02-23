<script setup>
import { ref } from 'vue';

const API_ENDPOINTS = {
  electronics: 'http://localhost:8081/estore/api/electronic',
  purchases: 'http://localhost:8081/estore/api/purchase',
  employees: 'http://localhost:8081/estore/api/employee',
  references: {
    positions: 'http://localhost:8081/estore/api/position',
    electronicsType: 'http://localhost:8081/estore/api/electronicsType',
    store: 'http://localhost:8081/estore/api/store',
    purchaseType: 'http://localhost:8081/estore/api/purchaseType'
  }
};

const categories = ref([
  { name: 'Электротовары', key: 'electronics', fetchData: true, isReference: false },
  { name: 'Покупки', key: 'purchases', fetchData: true, isReference: false },
  { name: 'Сотрудники', key: 'employees', fetchData: true, isReference: false },
  {
    name: 'Справочники',
    key: 'references',
    fetchData: false,
    isReference: true,
    expanded: false,
    subCategories: [
      { name: 'Должности', key: 'positions', fetchData: true },
      { name: 'Тип электроники', key: 'electronicsType', fetchData: true },
      { name: 'Магазин', key: 'store', fetchData: true },
      { name: 'Тип покупки', key: 'purchaseType', fetchData: true }
    ]
  }
]);

const selectedCategory = ref(null);


const tableData = ref([]);
const tableHeaders = ref([]);


const fetchData = async (categoryKey) => {
  let apiUrl = API_ENDPOINTS[categoryKey];

  if (!apiUrl) {

    for (const refCategory of categories.value.find(c => c.key === 'references')?.subCategories || []) {
      if (refCategory.key === categoryKey) {
        apiUrl = API_ENDPOINTS.references[categoryKey];
        break;
      }
    }
  }

  if (!apiUrl) return;

  try {
    const response = await fetch(apiUrl);
    if (!response.ok) throw new Error('Ошибка загрузки данных');

    const data = await response.json();
    if (data.length === 0) {
      tableHeaders.value = [];
      tableData.value = [];
      return;
    }

    tableHeaders.value = Object.keys(data[0]);
    tableData.value = data;
  } catch (error) {
    console.error('Ошибка:', error);
  }
};


const selectCategory = (category) => {
  if (category.isReference) {
    category.expanded = !category.expanded;
    return;
  }

  selectedCategory.value = category.name;
  fetchData(category.key);
};


const selectSubCategory = (subCategory) => {
  selectedCategory.value = subCategory.name;
  fetchData(subCategory.key);
};
</script>

<template>
  <div class="container">
    <aside class="sidebar">
      <h2>Разделы</h2>
      <template v-for="category in categories" :key="category.key">
        <button
            @click="selectCategory(category)"
            :class="{ active: selectedCategory === category.name }"
        >
          {{ category.name }}
        </button>


        <div v-if="category.isReference && category.expanded" class="subcategories">
          <button
              v-for="subCategory in category.subCategories"
              :key="subCategory.key"
              @click="selectSubCategory(subCategory)"
              :class="{ active: selectedCategory === subCategory.name }"
          >
            {{ subCategory.name }}
          </button>
        </div>
      </template>
    </aside>

    <main class="content">
      <h2 v-if="selectedCategory">Список - {{ selectedCategory }}</h2>
      <p v-else>Выберите категорию для отображения данных</p>


      <table v-if="tableHeaders.length > 0">
        <thead>
        <tr>
          <th v-for="header in tableHeaders" :key="header">
            {{ header }}
          </th>
        </tr>
        </thead>
        <tbody>
        <tr v-for="row in tableData" :key="row.id">
          <td v-for="header in tableHeaders" :key="header">
            {{ row[header] }}
          </td>
        </tr>
        </tbody>
      </table>
    </main>
  </div>
</template>

<style scoped>
/* Основной контейнер */
.container {
  display: flex;
  height: 100vh;
  background-color: #000;
  color: #fff;
}

/* Боковая панель */
.sidebar {
  width: 250px;
  background-color: #222;
  padding: 20px;
  box-sizing: border-box;
  overflow-y: auto;
  border-right: 1px solid #444;
}

.sidebar h2 {
  margin-top: 0;
  font-size: 20px;
  color: #fff;
  margin-bottom: 20px;
}

.sidebar button {
  background: none;
  border: 1px solid #444;
  color: #fff;
  padding: 12px;
  margin: 8px 0;
  text-align: left;
  width: 100%;
  font-size: 16px;
  cursor: pointer;
  transition: background 0.3s, border 0.3s;
  border-radius: 4px;
}

.sidebar button:hover {
  background-color: #444;
}

.sidebar button.active {
  background-color: #666;
  border-color: #fff;
}

.subcategories button {
  margin-left: 20px;
}

/* Контентная часть */
.content {
  flex-grow: 1;
  padding: 20px;
  box-sizing: border-box;
  overflow-y: auto;
}

.content h2, .content p {
  margin: 0 0 20px 0;
  font-size: 22px;
}

.content h2 {
  font-weight: 600;
}

/* Стили таблицы */
table {
  width: 100%;
  border-collapse: collapse;
  color: #fff;
  margin-top: 20px;
}

table th, table td {
  padding: 12px;
  text-align: left;
  border: 1px solid #444;
  word-wrap: break-word;
  overflow: hidden;
}

table th {
  background-color: #333;
  font-weight: bold;
}

table tbody tr:nth-child(even) {
  background-color: #222;
}

table tbody tr:nth-child(odd) {
  background-color: #111;
}

table tbody tr:hover {
  background-color: #444;
}

table td {
  word-wrap: break-word;
  overflow: hidden;
}

table td, table th {
  border-right: 1px solid #444;
}

table td:last-child, table th:last-child {
  border-right: none;
}

/* Прокрутка для таблицы */
.content table {
  max-height: calc(100vh - 180px); /* Высота таблицы с учетом отступов */
  overflow-y: auto;
  display: block;
  border-radius: 6px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
}

html, body {
  margin: 0;
  padding: 0;
  height: 100%;
  font-family: Arial, sans-serif;
}

.container {
  display: flex;
  flex-direction: row;
  min-height: 100%;
}

.sidebar {
  flex: 0 0 250px;
}

.content {
  flex: 1;
  overflow: hidden;
  display: flex;
  flex-direction: column;
}
</style>


<!--<style scoped>-->

<!--.container {-->
<!--  display: flex;-->
<!--  height: 100vh;-->
<!--  background: #111;-->
<!--  color: #ddd;-->
<!--}-->

<!--/* Левое меню */-->
<!--.sidebar {-->
<!--  width: 280px;-->
<!--  background: #222;-->
<!--  padding: 20px;-->
<!--  display: flex;-->
<!--  flex-direction: column;-->
<!--  border-right: 2px solid #444;-->
<!--}-->

<!--.sidebar h2 {-->
<!--  text-align: center;-->
<!--  margin-bottom: 20px;-->
<!--  font-size: 20px;-->
<!--  color: #fff;-->
<!--}-->

<!--/* Кнопки в меню */-->
<!--.sidebar button {-->
<!--  background: transparent;-->
<!--  border: none;-->
<!--  color: #ddd;-->
<!--  padding: 12px;-->
<!--  text-align: left;-->
<!--  cursor: pointer;-->
<!--  font-size: 16px;-->
<!--  width: 100%;-->
<!--  transition: 0.3s;-->
<!--}-->

<!--.sidebar button:hover {-->
<!--  background: #333;-->
<!--  color: #fff;-->
<!--}-->

<!--.sidebar button.active {-->
<!--  background: #444;-->
<!--  font-weight: bold;-->
<!--  color: #fff;-->
<!--}-->


<!--.subcategories {-->
<!--  padding-left: 15px;-->
<!--  display: flex;-->
<!--  flex-direction: column;-->
<!--  margin-top: 5px;-->
<!--}-->

<!--.subcategories button {-->
<!--  font-size: 14px;-->
<!--  padding: 8px;-->
<!--  background: #333;-->
<!--}-->

<!--.subcategories button:hover {-->
<!--  background: #555;-->
<!--}-->


<!--.content {-->
<!--  flex: 1;-->
<!--  padding: 20px;-->
<!--  display: flex;-->
<!--  flex-direction: column;-->
<!--  align-items: center;-->
<!--  justify-content: center;-->
<!--  overflow: auto;-->
<!--}-->


<!--.content h2 {-->
<!--  margin-bottom: 15px;-->
<!--  font-size: 22px;-->
<!--  color: #fff;-->
<!--  text-align: center;-->
<!--}-->


<!--table {-->
<!--  width: 90%;-->
<!--  border-collapse: collapse;-->
<!--  background: #222;-->
<!--  color: #ddd;-->
<!--  border-radius: 8px;-->
<!--  overflow: hidden;-->
<!--}-->

<!--th, td {-->
<!--  border: 1px solid #444;-->
<!--  padding: 12px;-->
<!--  text-align: left;-->
<!--}-->

<!--th {-->
<!--  background: #333;-->
<!--  font-weight: bold;-->
<!--  color: #fff;-->
<!--}-->

<!--td {-->
<!--  background: #222;-->
<!--}-->

<!--tr:hover td {-->
<!--  background: #333;-->
<!--}-->
<!--</style>-->

