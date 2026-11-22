import { useState, type FormEvent } from 'react'
import { Link } from 'react-router'
import { login } from '../services/api'
import { useAuthStore } from '../store/auth'
import { AuthError, AuthLayout } from './AuthPages'

export function CustomerLogin({ onSuccess }: { onSuccess: () => void }) {
  const setSession = useAuthStore((state) => state.setSession)
  const [email, setEmail] = useState('')
  const [password, setPassword] = useState('')
  const [error, setError] = useState('')
  const [pending, setPending] = useState(false)

  async function handleSubmit(event: FormEvent<HTMLFormElement>) {
    event.preventDefault()
    setError('')
    setPending(true)
    try {
      const session = await login(email, password)
      setSession(session)
      onSuccess()
    } catch (cause) {
      setError(cause instanceof Error ? cause.message : 'Не удалось войти. Проверьте данные и попробуйте ещё раз.')
    } finally {
      setPending(false)
    }
  }

  return (
    <AuthLayout title="С возвращением" subtitle="Войдите, чтобы продолжить." footer={<>Нет аккаунта? <Link to="/customer/register">Зарегистрироваться</Link></>}>
      <form className="auth-form" onSubmit={handleSubmit}>
        <label className="field-label" htmlFor="login-email">Электронная почта</label>
        <input id="login-email" className="form-input" type="email" autoComplete="email" placeholder="name@example.com" value={email} onChange={(event) => setEmail(event.target.value)} required />
        <label className="field-label" htmlFor="login-password">Пароль</label>
        <input id="login-password" className="form-input" type="password" autoComplete="current-password" placeholder="Введите пароль" value={password} onChange={(event) => setPassword(event.target.value)} required />
        {error && <AuthError error={error} />}
        <button className="button button-primary form-submit" type="submit" disabled={pending}>{pending ? 'Входим…' : 'Войти'} <span aria-hidden="true">→</span></button>
      </form>
    </AuthLayout>
  )
}
