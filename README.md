* { font-family: 'Inter', 'Noto Sans SC', sans-serif; -webkit-tap-highlight-color: transparent; }
body { background-color: #fbf7ff; overflow-x: hidden; }
.hide-scrollbar::-webkit-scrollbar { display: none; }
.hide-scrollbar { -ms-overflow-style: none; scrollbar-width: none; }
.safe-bottom { padding-bottom: env(safe-area-inset-bottom, 20px); }
.glass {
    background: rgba(255, 255, 255, 0.9);
    backdrop-filter: blur(12px);
    -webkit-backdrop-filter: blur(12px);
    border-bottom: 1px solid rgba(201, 181, 247, 0.2);
}
@keyframes fadeIn { from { opacity: 0; transform: translateY(10px); } to { opacity: 1; transform: translateY(0); } }
@keyframes slideUp { from { transform: translateY(100%); } to { transform: translateY(0); } }
.animate-fade-in { animation: fadeIn 0.4s ease-out; }
.animate-slide-up { animation: slideUp 0.3s ease-out; }
.page-transition { transition: opacity 0.3s ease, transform 0.3s ease; }
.page-hidden { opacity: 0; pointer-events: none; position: absolute; transform: scale(0.95); }
.page-active { opacity: 1; pointer-events: all; position: relative; transform: scale(1); }
.category-chip {
    transition: all 0.3s ease;
    background: rgba(255, 255, 255, 0.8);
    border: 1px solid rgba(201, 181, 247, 0.2);
}
.category-chip.active {
    background: linear-gradient(135deg, #c9b5f7, #b49be8);
    color: white;
    transform: scale(1.05);
    box-shadow: 0 4px 20px rgba(196, 181, 232, 0.4);
    border-color: transparent;
}
.outfit-card {
    background: white;
    border-radius: 20px;
    overflow: hidden;
    transition: all 0.3s ease;
    border: 1px solid rgba(201, 181, 247, 0.15);
    margin-bottom: 16px;
}
.item-chip {
    background: linear-gradient(135deg, #f5f0ff, #fbf7ff);
    border: 1px solid rgba(201, 181, 247, 0.3);
}
.color-dot {
    width: 12px;
    height: 12px;
    border-radius: 50%;
    border: 2px solid white;
    box-shadow: 0 0 0 1px rgba(0,0,0,0.1);
}
.match-score {
    background: linear-gradient(135deg, #c9b5f7, #b49be8);
    color: white;
    font-weight: 600;
}
.ai-thinking-dot {
    width: 8px;
    height: 8px;
    background: #c9b5f7;
    border-radius: 50%;
    display: inline-block;
    animation: bounce 1.4s infinite ease-in-out both;
}
.ai-thinking-dot:nth-child(1) { animation-delay: -0.32s; }
.ai-thinking-dot:nth-child(2) { animation-delay: -0.16s; }
@keyframes bounce {
    0%, 80%, 100% { transform: scale(0); }
    40% { transform: scale(1); }
}

/* 新增：上传功能和衣橱展示样式 */
.upload-container {
    padding: 20px;
}
.upload-box {
    border: 2px dashed #ddd0ff;
    border-radius: 20px;
    padding: 40px;
    text-align: center;
    cursor: pointer;
    transition: background-color 0.3s;
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    min-height: 200px;
}
.upload-box:hover {
    background-color: #f5f0ff;
}
#image-preview-container {
    position: relative;
    margin-top: 20px;
    max-width: 100%;
}
#image-preview {
    max-width: 100%;
    border-radius: 12px;
    box-shadow: 0 4px 12px rgba(0,0,0,0.1);
}
.processing-overlay {
    position: absolute;
    top: 0;
    left: 0;
    right: 0;
    bottom: 0;
    background: rgba(255, 255, 255, 0.8);
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    border-radius: 12px;
    backdrop-filter: blur(4px);
}
.fab {
    position: fixed;
    bottom: 100px; /* 放在导航栏上方 */
    right: calc(50% - 224px + 24px); /* 与主内容区对齐 */
    z-index: 45;
    width: 56px;
    height: 56px;
    border-radius: 50%;
    background: linear-gradient(135deg, #c9b5f7, #b49be8);
    color: white;
    display: flex;
    align-items: center;
    justify-content: center;
    box-shadow: 0 4px 20px rgba(196, 181, 232, 0.5);
    transition: all 0.3s ease;
    border: none;
    cursor: pointer;
}
.fab:hover {
    transform: scale(1.1);
}
