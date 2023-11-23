(() => {
	'use strict';

	const storedTheme = localStorage.getItem('theme');
	const selectors = [
		'#search-box',
		'#search-share-box',
		'.add-email-input',
		'.search-item',
		'.search-result',
		'.search-share-result',
		'.custom-alert-theme',
		'.text-gray',
		'.text-title-gray',
		'.cus-nav-item .active',
		'.cus-nav-item'
	];
	const getPreferredTheme = () => {
		if (storedTheme) {
			return storedTheme;
		}
		return window.matchMedia('(prefers-color-scheme: dark)').matches ? 'dark' : 'light';
	};

	const setTheme = function(theme) {
		const isChecked = theme === 'dark';
		document.getElementById('tonggleTheme').checked = isChecked;
		if (theme === 'auto' && window.matchMedia('(prefers-color-scheme: dark)').matches) {
			document.documentElement.setAttribute('data-bs-theme', 'dark');
			selectors.forEach((selector) => {
				if (selector.startsWith('#')) {
					const item = document.getElementById(selector);
					if (item) {
						item.classList.add('dark');
					}
				} else {
					document.querySelectorAll(selector).forEach((item) => {
						item.classList.add('dark');
					});
				}
			});
			document.querySelectorAll('.search-item').forEach((item) => {
				item.classList.add('dark');
			});
		} else {
			document.documentElement.setAttribute('data-bs-theme', theme);
			selectors.forEach((selector) => {
				if (selector.startsWith('#')) {
					const item = document.querySelector(selector);
					if (item) {
						if (isChecked) {
							item.classList.add('dark');
						} else {
							item.classList.remove('dark');
						}
					}
				} else {
					document.querySelectorAll(selector).forEach((item) => {
						if (isChecked) {
							item.classList.add('dark');
						} else {
							item.classList.remove('dark');
						}
					});
				}
			});
		}
	};

	setTheme(getPreferredTheme());

	window.addEventListener('DOMContentLoaded', () => {
		const tonggleThemeBtn = document.getElementById('tonggleTheme');
		tonggleThemeBtn.addEventListener('change', () => {
			const theme = tonggleThemeBtn.checked ? 'dark' : 'light';
			localStorage.setItem('theme', theme);
			setTheme(theme);
		});
	});
})();
