import { useState } from 'react'
import type { UseQueryResult } from '@tanstack/react-query'
import type { Category } from '../types/api'
import { CustomerContent } from '../components/customer/CustomerContent'
import { CustomerSidebar } from '../components/customer/CustomerSidebar'

export function CustomerHome({ categories }: { categories: UseQueryResult<Category[], Error> }) {
  const [selectedCategoryId, setSelectedCategoryId] = useState<number | null>(null)
  const [selectedSubcategoryId, setSelectedSubcategoryId] = useState<number | null>(null)
  const categoryList = categories.data ?? []

  function selectCategory(categoryId: number) {
    setSelectedCategoryId(categoryId)
    setSelectedSubcategoryId(null)
  }

  function selectSubcategory(categoryId: number, subcategoryId: number) {
    setSelectedCategoryId(categoryId)
    setSelectedSubcategoryId(subcategoryId)
  }

  return (
    <div className="page-content inner-page customer-catalog-page">
      {categories.isPending ? <p className="muted-message">Загружаем категории…</p> : categories.isError ? (
        <div className="notice notice-error"><span>{categories.error.message}</span><button className="button button-quiet" onClick={() => void categories.refetch()}>Повторить</button></div>
      ) : categoryList.length === 0 ? <div className="empty-state"><span className="empty-icon">✳</span><h2>Категории скоро появятся</h2></div> : (
        <div className="customer-catalog-layout">
          <CustomerSidebar
            categories={categoryList}
            selectedCategoryId={selectedCategoryId}
            selectedSubcategoryId={selectedSubcategoryId}
            onSelectCategory={selectCategory}
            onSelectSubcategory={selectSubcategory}
          />
          <CustomerContent categoryId={selectedCategoryId} subcategoryId={selectedSubcategoryId} />
        </div>
      )}
    </div>
  )
}
