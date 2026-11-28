import { create } from 'zustand'
import type { AuthResponse, User } from '../types/api'
import { getCurrentUser } from '../services/api'

const STORAGE_KEY = 'marketplace-api-key'

type AuthState = {
  user: User | null
  initialized: boolean
  setSession: (session: AuthResponse) => void
  initialize: () => Promise<void>
  logout: () => void
}

export const useAuthStore = create<AuthState>((set) => ({
  user: null,
  initialized: false,
  setSession: (session) => {
    localStorage.setItem(STORAGE_KEY, session.apiKey)
    set({ user: session, initialized: true })
  },
  initialize: async () => {
    const apiKey = localStorage.getItem(STORAGE_KEY)
    if (!apiKey) {
      set({ initialized: true })
      return
    }

    try {
      const user = await getCurrentUser(apiKey)
      set({ user, initialized: true })
    } catch {
      localStorage.removeItem(STORAGE_KEY)
      set({ user: null, initialized: true })
    }
  },
  logout: () => {
    localStorage.removeItem(STORAGE_KEY)
    set({ user: null, initialized: true })
  },
}))
