/**
 * ====================================
 * JUEGO DE HALLOWEEN - ATRAPA CALABAZAS
 * JavaScript para 2º DAM
 * ====================================
 */

// Variables del juego
const gameContainer = document.getElementById('gameContainer');
const player = document.getElementById('player');
const scoreDisplay = document.getElementById('score');
const livesDisplay = document.getElementById('lives');
const gameOverScreen = document.getElementById('gameOver');
const finalScoreDisplay = document.getElementById('finalScore');
const restartBtn = document.getElementById('restartBtn');

let score = 0;
let lives = 3;
let playerPosition = 270; // Posición inicial centrada
let gameActive = true;
let fallingObjects = [];
let gameSpeed = 2;
let spawnInterval;
let gameLoop;

// Configuración del juego
const PLAYER_SPEED = 15;
const CONTAINER_WIDTH = 600;
const CONTAINER_HEIGHT = 500;
const PLAYER_WIDTH = 60;
const OBJECT_SIZE = 40;

/**
 * Inicializar la posición del jugador
 */
function initializePlayer() {
    player.style.left = playerPosition + 'px';
}

/**
 * Control del jugador con teclado
 */
function setupKeyboardControls() {
    document.addEventListener('keydown', (e) => {
        if (!gameActive) return;

        if (e.key === 'ArrowLeft') {
            playerPosition = Math.max(0, playerPosition - PLAYER_SPEED);
        } else if (e.key === 'ArrowRight') {
            playerPosition = Math.min(CONTAINER_WIDTH - PLAYER_WIDTH, playerPosition + PLAYER_SPEED);
        }
        player.style.left = playerPosition + 'px';
    });
}

/**
 * Control del jugador con ratón
 */
function setupMouseControls() {
    gameContainer.addEventListener('mousemove', (e) => {
        if (!gameActive) return;

        const rect = gameContainer.getBoundingClientRect();
        playerPosition = Math.max(0, Math.min(CONTAINER_WIDTH - PLAYER_WIDTH, e.clientX - rect.left - PLAYER_WIDTH / 2));
        player.style.left = playerPosition + 'px';
    });
}

/**
 * Crear objeto que cae (calabaza o fantasma)
 */
function createFallingObject() {
    const obj = document.createElement('div');
    obj.className = 'falling-object';

    // 70% calabazas (buenas), 30% fantasmas (malos)
    const isGood = Math.random() < 0.7;
    obj.textContent = isGood ? '🎃' : '👻';
    obj.dataset.type = isGood ? 'good' : 'bad';

    // Posición aleatoria horizontal
    const randomX = Math.random() * (CONTAINER_WIDTH - OBJECT_SIZE);
    obj.style.left = randomX + 'px';
    obj.style.top = '-50px';

    gameContainer.appendChild(obj);
    fallingObjects.push({
        element: obj,
        x: randomX,
        y: -50,
        type: isGood ? 'good' : 'bad'
    });
}

/**
 * Detectar colisión entre jugador y objeto
 * @param {Object} obj - Objeto que cae
 * @returns {boolean} - True si hay colisión
 */
function checkCollision(obj) {
    const objRect = {
        left: obj.x,
        right: obj.x + OBJECT_SIZE,
        top: obj.y,
        bottom: obj.y + OBJECT_SIZE
    };

    const playerRect = {
        left: playerPosition,
        right: playerPosition + PLAYER_WIDTH,
        top: CONTAINER_HEIGHT - 70,
        bottom: CONTAINER_HEIGHT - 10
    };

    return objRect.left < playerRect.right &&
        objRect.right > playerRect.left &&
        objRect.top < playerRect.bottom &&
        objRect.bottom > playerRect.top;
}

/**
 * Actualizar el estado del juego
 */
function update() {
    if (!gameActive) return;

    // Actualizar objetos que caen
    for (let i = fallingObjects.length - 1; i >= 0; i--) {
        const obj = fallingObjects[i];
        obj.y += gameSpeed;
        obj.element.style.top = obj.y + 'px';

        // Comprobar colisión
        if (checkCollision(obj)) {
            if (obj.type === 'good') {
                score += 10;
                scoreDisplay.textContent = score;

                // Aumentar velocidad cada 50 puntos
                if (score % 50 === 0) {
                    gameSpeed += 0.5;
                }
            } else {
                lives--;
                livesDisplay.textContent = lives;

                if (lives <= 0) {
                    endGame();
                }
            }

            obj.element.remove();
            fallingObjects.splice(i, 1);
            continue;
        }

        // Eliminar si sale de pantalla
        if (obj.y > CONTAINER_HEIGHT) {
            obj.element.remove();
            fallingObjects.splice(i, 1);
        }
    }
}

/**
 * Finalizar el juego
 */
function endGame() {
    gameActive = false;
    clearInterval(spawnInterval);
    clearInterval(gameLoop);

    finalScoreDisplay.textContent = score;
    gameOverScreen.style.display = 'block';
}

/**
 * Reiniciar el juego
 */
function restartGame() {
    // Limpiar objetos existentes
    fallingObjects.forEach(obj => obj.element.remove());
    fallingObjects = [];

    // Reiniciar variables
    score = 0;
    lives = 3;
    gameSpeed = 2;
    gameActive = true;
    playerPosition = 270;

    // Actualizar interfaz
    scoreDisplay.textContent = score;
    livesDisplay.textContent = lives;
    player.style.left = playerPosition + 'px';
    gameOverScreen.style.display = 'none';

    // Iniciar juego
    startGame();
}

/**
 * Iniciar el juego
 */
function startGame() {
    // Crear objetos cada 1 segundo
    spawnInterval = setInterval(createFallingObject, 1000);

    // Actualizar juego cada 30ms (~33 FPS)
    gameLoop = setInterval(update, 30);
}

/**
 * Configurar event listeners
 */
function setupEventListeners() {
    restartBtn.addEventListener('click', restartGame);
}

/**
 * Inicialización del juego
 */
function initGame() {
    initializePlayer();
    setupKeyboardControls();
    setupMouseControls();
    setupEventListeners();
    startGame();
}

// Iniciar el juego cuando el DOM esté listo
document.addEventListener('DOMContentLoaded', initGame);