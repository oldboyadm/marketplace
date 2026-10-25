import './style.css'

const buttonSync = document.querySelector('.button-sync');
const buttonAsync = document.querySelector('.button-async');

function syncTimeout() {
    console.log('Синхронная пауза на 5 сек - СТАРТ')
    const start = Date.now();

    while (Date.now() - start < 5000) {
        // ждем
    }
    console.log('Синхронная пауза на 5 сек - ФИНИШ')
}

function asyncTimeout() {
    console.log('Асинхронная пауза на 5 сек - СТАРТ')
    setTimeout(() => {
        console.log('Асинхронная пауза на 5 сек - ФИНИШ')
    }, 5000)
}

buttonSync.addEventListener('click', syncTimeout)

buttonAsync.addEventListener('click', asyncTimeout)
