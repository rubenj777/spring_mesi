const btnNew = document.getElementById('btnNew');
const btnInbox = document.getElementById('btnInbox');
const btnSent = document.getElementById('btnSent');
const contentNew = document.getElementById('contentNew');
const contentInbox = document.getElementById('contentInbox');
const contentSent = document.getElementById('contentSent');

btnNew.addEventListener('click', () => {
    contentNew.style.display = 'block';
    contentInbox.style.display = 'none';
    contentSent.style.display = 'none';
});

btnInbox.addEventListener('click', () => {
    contentNew.style.display = 'none';
    contentInbox.style.display = 'block';
    contentSent.style.display = 'none';
});

btnSent.addEventListener('click', () => {
    contentNew.style.display = 'none';
    contentInbox.style.display = 'none';
    contentSent.style.display = 'block';
});