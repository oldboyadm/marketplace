import type { AuthResponse, Category, Product, User } from '../types/api'

const API_BASE = (import.meta.env.VITE_API_BASE_URL ?? 'http://localhost:8080').replace(/\/$/, '')

async function request<T>(path: string, init?: RequestInit): Promise<T> {
  const response = await fetch(`${API_BASE}${path}`, {
    ...init,
    headers: { 'Content-Type': 'application/json', ...init?.headers },
  })

  if (!response.ok) {
    const body = await response.json().catch(() => null) as { message?: string } | null
    throw new Error(body?.message || 'Не удалось выполнить запрос. Попробуйте ещё раз.')
  }

  return response.json() as Promise<T>
}

export const getCategories = () => request<Category[]>('/categories')
export const getProducts = () => request<Product[]>('/products')

export const login = (email: string, password: string) =>
  request<AuthResponse>('/auth/login', {
    method: 'POST',
    body: JSON.stringify({ name: '', email, password }),
  })

export const register = (name: string, email: string, password: string) =>
  request<AuthResponse>('/auth/register', {
    method: 'POST',
    body: JSON.stringify({ name, email, password }),
  })

export const getCurrentUser = (apiKey: string) =>
  request<User>('/users/me', { headers: { Authorization: apiKey } })
