export type User = {
  id: number
  name: string
  email: string
  apiKey?: string
  createdAt?: string
  updatedAt?: string
}

export type Characteristic = {
  id: number
  name: string
}

export type Subcategory = {
  id: number
  name: string
  characteristics: Characteristic[] | null
}

export type Category = {
  id: number
  name: string
  subcategories: Subcategory[]
}

export type Product = {
  id: number
  name: string
  description: string
  photoUrl: string
  price: number
  subcategory: Subcategory
  characteristics: { id: number; value: string; characteristic: Characteristic }[]
}

export type AuthResponse = User & { apiKey: string }
