// 模拟的AI搭配数据库
const outfitDatabase = {
    '工装裙': [
        {
            id: 'work-001',
            title: '街头酷飒风',
            matchScore: 95,
            mainImage: 'https://images.unsplash.com/photo-1550614000-4b9519e02a48?w=800&h=600&fit=crop',
            items: [
                { name: '军绿工装裙', category: 'bottoms', color: '#4B5320', image: 'https://images.unsplash.com/photo-1583496661160-fb5886a0aaaa?w=200&h=200&fit=crop' },
                { name: '白色短款卫衣', category: 'tops', color: '#FFFFFF', image: 'https://images.unsplash.com/photo-1556905055-8f358a7a47b2?w=200&h=200&fit=crop' },
                { name: '黑色马丁靴', category: 'shoes', color: '#000000', image: 'https://images.unsplash.com/photo-1542291026-7eec264c27ff?w=200&h=200&fit=crop' },
                { name: '银链项链', category: 'accessories', color: '#C0C0C0', image: 'https://images.unsplash.com/photo-1599643478518-a784e5dc4c8f?w=200&h=200&fit=crop' }
            ],
            colorPalette: ['#4B5320', '#FFFFFF', '#000000', '#C0C0C0'],
            styleTags: ['街头', '酷飒', '美式复古'],
            occasion: '逛街 / 音乐节 / 校园',
            reason: '工装裙的硬朗线条与短款卫衣的休闲感完美平衡，马丁靴增加酷感，银色配饰点缀提升精致度。',
            views: '3.2k'
        },
        {
            id: 'work-002',
            title: '温柔甜酷风',
            matchScore: 88,
            mainImage: 'https://images.unsplash.com/photo-1515886657613-9f3515b0c78f?w=800&h=600&fit=crop',
            items: [
                { name: '卡其工装裙', category: 'bottoms', color: '#D4C4B0', image: 'https://images.unsplash.com/photo-1583496661160-fb5886a0bbbb?w=200&h=200&fit=crop' },
                { name: '粉色针织背心', category: 'tops', color: '#FFB6C1', image: 'https://images.unsplash.com/photo-1576566588028-4147f3842f27?w=200&h=200&fit=crop' },
                { name: '白色老爹鞋', category: 'shoes', color: '#FFFFFF', image: 'https://images.unsplash.com/photo-1560769629-975ec94e6a86?w=200&h=200&fit=crop' },
                { name: '帆布托特包', category: 'accessories', color: '#F5F5DC', image: 'https://images.unsplash.com/photo-1544816155-12df9643f363?w=200&h=200&fit=crop' }
            ],
            colorPalette: ['#D4C4B0', '#FFB6C1', '#FFFFFF', '#F5F5DC'],
            styleTags: ['甜酷', '温柔', '韩系'],
            occasion: '约会 / 下午茶 / 拍照',
            reason: '卡其色的柔和与粉色的甜美碰撞，工装元素中和过于甜腻的感觉，打造可盐可甜的平衡感。',
            views: '2.8k'
        }
    ],
    '约会穿搭': [
        {
            id: 'date-001',
            title: '法式浪漫风',
            matchScore: 92,
            mainImage: 'https://images.unsplash.com/photo-1595777457583-95e059d581b8?w=800&h=600&fit=crop',
            items: [
                { name: '碎花茶歇裙', category: 'dresses', color: '#E6E6FA', image: 'https://images.unsplash.com/photo-1599643477877-530eb83abc8e?w=200&h=200&fit=crop' },
                { name: '米色针织开衫', category: 'outerwear', color: '#F5F5DC', image: 'https://images.unsplash.com/photo-1434389677669-e08b4cac3105?w=200&h=200&fit=crop' },
                { name: '裸色芭蕾鞋', category: 'shoes', color: '#FFDAB9', image: 'https://images.unsplash.com/photo-1543163521-1bf539c55dd2?w=200&h=200&fit=crop' },
                { name: '草编手提包', category: 'accessories', color: '#DEB887', image: 'https://images.unsplash.com/photo-1584677695244-611f164372a9?w=200&h=200&fit=crop' }
            ],
            colorPalette: ['#E6E6FA', '#F5F5DC', '#FFDAB9', '#DEB887'],
            styleTags: ['法式', '浪漫', '田园'],
            occasion: '约会 / 野餐 / 旅行',
            reason: '经典的法式组合，茶歇裙的优雅与针织开衫的温柔相得益彰，营造出不费力的时髦感。',
            views: '5.1k'
        }
    ],
    '通勤套装': [], '卫衣': [], '连衣裙': [], '大衣': []
};

let currentPage = 'wardrobe';
let backgroundRemoval;

// --- 页面切换和通用函数 ---
function switchPage(pageId) {
    document.querySelectorAll('.page-transition').forEach(page => {
        page.classList.remove('page-active');
        page.classList.add('page-hidden');
    });
    document.getElementById(`page-${pageId}`).classList.add('page-active');
    document.getElementById(`page-${pageId}`).classList.remove('page-hidden');

    document.querySelectorAll('.nav-btn').forEach(btn => {
        if (btn.dataset.page === pageId) {
            btn.classList.add('text-primary-400');
            btn.classList.remove('text-slate-400');
        } else {
            btn.classList.add('text-slate-400');
            btn.classList.remove('text-primary-400');
        }
    });
    currentPage = pageId;

    // 根据页面执行特定渲染
    if (pageId === 'wardrobe') {
        renderWardrobe();
    } else if (pageId === 'upload') {
        resetUploadPage();
    }
}

function showToast(message) {
    const toast = document.getElementById('toast');
    toast.textContent = message;
    toast.style.opacity = '1';
    setTimeout(() => {
        toast.style.opacity = '0';
    }, 2000);
}

// --- AI发现页面逻辑 ---
function startAISearch() {
    const query = document.getElementById('ai-search-input').value;
    if (!query) return;
    
    document.getElementById('default-inspiration').style.display = 'none';
    document.getElementById('ai-results').style.display = 'none';
    document.getElementById('ai-thinking').style.display = 'block';

    const statuses = ["连接时尚数据库...", "分析流行趋势...", "生成搭配方案..."];
    let statusIndex = 0;
    const statusEl = document.getElementById('ai-status');
    statusEl.textContent = statuses[statusIndex];
    const interval = setInterval(() => {
        statusIndex++;
        if (statusIndex < statuses.length) {
            statusEl.textContent = statuses[statusIndex];
        } else {
            clearInterval(interval);
        }
    }, 1000);

    setTimeout(() => {
        document.getElementById('ai-thinking').style.display = 'none';
        const results = outfitDatabase[query] || [];
        renderInspiration(results);
        document.getElementById('ai-results').style.display = 'block';
    }, 3000);
}

function quickSearch(term) {
    document.getElementById('ai-search-input').value = term;
    startAISearch();
}

function renderInspiration(results) {
    const grid = document.getElementById('inspiration-grid');
    grid.innerHTML = '';
    document.getElementById('result-count').textContent = `${results.length}套`;

    if (results.length === 0) {
        grid.innerHTML = `<p class="text-center text-slate-500 py-8">未找到相关搭配，试试别的关键词？</p>`;
        return;
    }

    results.forEach(outfit => {
        const card = document.createElement('div');
        card.className = 'outfit-card animate-fade-in';
        card.innerHTML = `
            <div class="relative">
                <img src="${outfit.mainImage}" class="w-full h-48 object-cover">
                <div class="absolute top-3 right-3 match-score text-xs px-2 py-1 rounded-full">${outfit.matchScore}分</div>
            </div>
            <div class="p-4">
                <h4 class="font-semibold text-slate-800">${outfit.title}</h4>
                <p class="text-xs text-slate-500 mt-1 mb-3">${outfit.occasion}</p>
                <div class="flex items-center gap-2 mb-3">
                    ${outfit.colorPalette.map(color => `<div class="color-dot" style="background-color: ${color};"></div>`).join('')}
                </div>
                <p class="text-xs text-slate-600 leading-relaxed mb-4">${outfit.reason}</p>
                <div class="flex flex-wrap gap-2">
                    ${outfit.styleTags.map(tag => `<span class="text-xs bg-primary-50 text-primary-600 px-2 py-1 rounded-full">${tag}</span>`).join('')}
                </div>
            </div>
        `;
        grid.appendChild(card);
    });
}

// --- 用户上传与AI抠图逻辑 ---
async function initBackgroundRemoval() {
    try {
        if (!window.imglyRemoveBackground) {
            console.error("imglyRemoveBackground library not loaded.");
            showToast("AI抠图库加载失败");
            return;
        }
        backgroundRemoval = await new window.imglyRemoveBackground({
            apiKey: 'lite', // 这是一个免费但有速率限制的key
            model: 'lite',
            progress: (key, current, total) => {
                const status = document.getElementById('processing-status');
                if (status) {
                    status.textContent = `处理中... ${Math.round((current / total) * 100)}%`;
                }
            }
        });
    } catch (error) {
        console.error("Failed to initialize background removal:", error);
        showToast("AI抠图服务初始化失败");
    }
}

function triggerFileUpload() {
    document.getElementById('file-input').click();
}

function handleFileSelect(event) {
    const file = event.target.files[0];
    if (!file || !file.type.startsWith('image/')) {
        showToast("请选择一个图片文件");
        return;
    }

    const reader = new FileReader();
    reader.onload = e => {
        document.getElementById('image-preview').src = e.target.result;
        document.querySelector('.upload-box').style.display = 'none';
        document.getElementById('image-preview-container').style.display = 'block';
        document.getElementById('remove-bg-btn').style.display = 'inline-flex';
        document.getElementById('save-btn').style.display = 'none';
    };
    reader.readAsDataURL(file);
}

async function removeBackground() {
    if (!backgroundRemoval) {
        showToast("AI抠图服务不可用");
        return;
    }

    const preview = document.getElementById('image-preview');
    if (!preview.src) return;

    document.getElementById('processing-overlay').style.display = 'flex';
    document.getElementById('remove-bg-btn').disabled = true;

    try {
        const resultBlob = await backgroundRemoval.run(preview.src);
        const url = URL.createObjectURL(resultBlob);
        preview.src = url;
        document.getElementById('save-btn').style.display = 'inline-flex';
        document.getElementById('remove-bg-btn').style.display = 'none';
    } catch (error) {
        console.error("Background removal failed:", error);
        showToast("抠图失败，请稍后再试");
    } finally {
        document.getElementById('processing-overlay').style.display = 'none';
        document.getElementById('remove-bg-btn').disabled = false;
    }
}

function saveToWardrobe() {
    const imageUrl = document.getElementById('image-preview').src;
    if (!imageUrl.startsWith('blob:')) {
        showToast("请先完成抠图再保存");
        return;
    }

    let wardrobe = JSON.parse(localStorage.getItem('wardrobeItems')) || [];
    const newItem = {
        id: `user-${Date.now()}`,
        image: imageUrl,
        added: new Date().toISOString()
    };
    wardrobe.push(newItem);
    localStorage.setItem('wardrobeItems', JSON.stringify(wardrobe));

    showToast("已保存到我的衣橱！");
    switchPage('wardrobe');
}

function resetUploadPage() {
    const fileInput = document.getElementById('file-input');
    if(fileInput) fileInput.value = '';
    document.getElementById('image-preview').src = '';
    document.querySelector('.upload-box').style.display = 'flex';
    document.getElementById('image-preview-container').style.display = 'none';
    document.getElementById('remove-bg-btn').style.display = 'none';
    document.getElementById('save-btn').style.display = 'none';
}

// --- 衣橱页面逻辑 ---
function renderWardrobe() {
    const wardrobeGrid = document.getElementById('wardrobe-grid');
    if (!wardrobeGrid) return;

    const wardrobeItems = JSON.parse(localStorage.getItem('wardrobeItems')) || [];
    wardrobeGrid.innerHTML = '';

    if (wardrobeItems.length === 0) {
        wardrobeGrid.innerHTML = `
            <div class="col-span-3 text-center text-slate-400 py-10">
                <svg class="mx-auto h-12 w-12 text-gray-300" fill="none" viewBox="0 0 24 24" stroke="currentColor" aria-hidden="true">
                    <path vector-effect="non-scaling-stroke" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M9 13h6m-3-3v6m-9 1V7a2 2 0 012-2h4l2 2h4a2 2 0 012 2v10a2 2 0 01-2 2H5a2 2 0 01-2-2z" />
                </svg>
                <h3 class="mt-2 text-sm font-medium text-gray-900">衣橱还是空的</h3>
                <p class="mt-1 text-sm text-gray-500">快去上传你的第一件单品吧！</p>
            </div>`;
        return;
    }

    wardrobeItems.reverse().forEach(item => {
        const itemEl = document.createElement('div');
        itemEl.className = 'bg-white rounded-2xl p-2 border border-primary-100 aspect-square flex items-center justify-center shadow-sm';
        itemEl.innerHTML = `<img src="${item.image}" class="max-w-full max-h-full object-contain">`;
        wardrobeGrid.appendChild(itemEl);
    });
}

// --- 初始化 ---
document.addEventListener('DOMContentLoaded', () => {
    initBackgroundRemoval();
    switchPage('wardrobe'); // 默认显示衣橱页面
    
    document.getElementById('fab-upload').addEventListener('click', () => {
        switchPage('upload');
    });
});
