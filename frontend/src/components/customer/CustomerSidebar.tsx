import { useState } from 'react'
import { ChevronDown } from 'lucide-react'
import { Button } from '@/components/ui/button'
import { Collapsible, CollapsibleContent, CollapsibleTrigger } from '@/components/ui/collapsible'
import type { Category } from '../../types/api'

type Props = {
  categories: Category[]
  selectedCategoryId: number | null
  selectedSubcategoryId: number | null
  onSelectCategory: (categoryId: number) => void
  onSelectSubcategory: (categoryId: number, subcategoryId: number) => void
}

export function CustomerSidebar({
  categories,
  selectedCategoryId,
  selectedSubcategoryId,
  onSelectCategory,
  onSelectSubcategory,
}: Props) {
  const [expandedCategoryId, setExpandedCategoryId] = useState<number | null>(null)

  return (
    <aside className="categories-sidebar" aria-label="Категории">
      <span className="sidebar-title">КАТЕГОРИИ</span>
      {categories.map((category) => {
        const isExpanded = expandedCategoryId === category.id
        const isSelected = selectedCategoryId === category.id
        const isCategoryFilter = isSelected && selectedSubcategoryId === null

        return (
          <Collapsible
            className={`category-sidebar-group${isSelected ? ' selected' : ''}`}
            key={category.id}
            open={isExpanded}
            onOpenChange={(open) => setExpandedCategoryId(open ? category.id : null)}
          >
            <div className="category-sidebar-row">
              <Button
                className="category-nav-item"
                variant="ghost"
                type="button"
                aria-pressed={isCategoryFilter}
                onClick={() => onSelectCategory(category.id)}
              >
                <span className="category-nav-label">{category.name}</span>
              </Button>
              <CollapsibleTrigger asChild>
                <Button
                  className="category-expand-button"
                  variant="ghost"
                  size="icon"
                  type="button"
                  aria-label={`${isExpanded ? 'Скрыть' : 'Показать'} подкатегории: ${category.name}`}
                  disabled={!category.subcategories?.length}
                >
                  <ChevronDown className={`category-expand-chevron${isExpanded ? ' expanded' : ''}`} aria-hidden="true" />
                </Button>
              </CollapsibleTrigger>
            </div>
            {category.subcategories?.length ? (
              <CollapsibleContent className="subcategory-nav-list">
                {category.subcategories.map((subcategory) => (
                  <Button
                    className={`subcategory-nav-item${selectedSubcategoryId === subcategory.id ? ' selected' : ''}`}
                    key={subcategory.id}
                    type="button"
                    variant="ghost"
                    aria-pressed={selectedSubcategoryId === subcategory.id}
                    onClick={() => onSelectSubcategory(category.id, subcategory.id)}
                  >
                    {subcategory.name}
                  </Button>
                ))}
              </CollapsibleContent>
            ) : null}
          </Collapsible>
        )
      })}
    </aside>
  )
}
