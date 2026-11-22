import { useState, type FormEvent } from 'react'
import { Link } from 'react-router'
import { register } from '../services/api'
import { useAuthStore } from '../store/auth'
import { AuthError, AuthLayout } from './AuthPages'

export function CustomerRegister({ onSuccess }: { onSuccess: () => void }) {
  const setSession = useAuthStore((state) => state.setSession)
  const [name, setName] = useState('')
  const [email, setEmail] = useState('')
  const [password, setPassword] = useState('')
  const [error, setError] = useState('')
  const [pending, setPending] = useState(false)

  async function handleSubmit(event: FormEvent<HTMLFormElement>) {
    event.preventDefault()
    setError('')
    setPending(true)
    try {
      const session = await register(name, email, password)
      setSession(session)
      onSuccess()
    } catch (cause) {
      setError(cause instanceof Error ? cause.message : 'Не удалось создать аккаунт. Попробуйте ещё раз.')
    } finally {
      setPending(false)
    }
  }

  return (
    <AuthLayout title="Создать аккаунт" subtitle="Регистрация займёт всего минуту." footer={<>Уже есть аккаунт? <Link to="/customer/login">Войти</Link></>}>
      <form className="auth-form" onSubmit={handleSubmit}>
        <label className="field-label" htmlFor="register-name">Ваше имя</label>
        <input id="register-name" className="form-input" type="text" autoComplete="name" placeholder="Как к вам обращаться?" value={name} onChange={(event) => setName(event.target.value)} required />
        <label className="field-label" htmlFor="register-email">Электронная почта</label>
        <input id="register-email" className="form-input" type="email" autoComplete="email" placeholder="name@example.com" value={email} onChange={(event) => setEmail(event.target.value)} required />
        <label className="field-label" htmlFor="register-password">Пароль</label>
        <input id="register-password" className="form-input" type="password" autoComplete="new-password" placeholder="Придумайте пароль" minLength={6} value={password} onChange={(event) => setPassword(event.target.value)} required />
        {error && <AuthError error={error} />}
        <button className="button button-primary form-submit" type="submit" disabled={pending}>{pending ? 'Создаём аккаунт…' : 'Зарегистрироваться'} <span aria-hidden="true">→</span></button>
      </form>
    </AuthLayout>
  )
}
