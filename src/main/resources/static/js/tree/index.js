$(function () {
    queryFileTree();
});

function queryFileTree() {
    $.ajax({
        url: ctx + "/tree/files/",
        type: "GET",
        success: function (json) {
            console.log(json);
            tree.setData('.filetree', JSON.parse(json));
        }
    });
}

/** tree.js zyj 2018.4.22 */
(function (name) {
    var tree, outer, defaultDateFormat;

    outer = {
        setData: setData,
    };

    defaultDateFormat = {
        unfold: true,
        file: 'file',
        name: 'fileName',
        ansPath: 'absPath',
        childName: 'children'
    };

    function getDataFormat(dataFormat) {
        let index;
        if (!dataFormat) {
            return defaultDateFormat;
        }
        for (index in defaultDateFormat) {
            dataFormat[index] = typeof dataFormat[index] == 'undefined' ? defaultDateFormat[index] : dataFormat[index];
        }
        return dataFormat
    }

    function initTreeJs(name) {
        let tree;
        if (checkTreeNameUsed(name)) {
            return;
        }
        window[name] = outer;
        initFoldIcon($('.filetree'));
    }

    function checkTreeNameUsed(name) {
        if (window[name]) {
            console.error("The window object name [" + name + "] has been used, tree.js can't be loaded! You can try another name.");
            return true;
        }
        return false;
    }

    function initFoldIcon(target) {
        // target.on('click', 'span>i.fa').on('click', 'span>i.fa', function (e) {
        //     var ele = $(e.target);
        //     if (ele.hasClass('fa-minus-circle')) {
        //         ele.removeClass('fa-minus-circle').addClass('fa-plus-circle').parent().next('ul').hide(200);
        //     } else if (ele.hasClass('fa-plus-circle')) {
        //         ele.removeClass('fa-plus-circle').addClass('fa-minus-circle').parent().next('ul').show(200);
        //     }
        // })
    }

    function getJqueryObjectBySelector(selector) {
        var ele = $(selector);
        if (typeof selector != 'string') {
            console.error("The first parameter jquery selector [" + selector + "] must be a string!");
            return;
        }
        if (!ele.hasClass('filetree')) {
            ele = ele.find('.filetree');
        }
        if (ele.length !== 1) {
            console.error("The selector [" + selector + "] expect only one element!");
            return;
        }
        return ele;
    }

    function setData(selector, data, dataFormat) {
        const ele = getJqueryObjectBySelector(selector);
        if (!ele) {
            return;
        }
        if (!data) {
            return;
        }
        if (!data.length) {
            data = [data];
        }
        dataFormat = getDataFormat(dataFormat);
        dataFormat.topElement = true;
        ele.empty().append(getTreeList(data, dataFormat));
        initFoldIcon(ele);
    }

    function getTreeList(data, dataFormat) {
        var i, single, name, file, children, childDataFormat,
            array = [];
        childDataFormat = dataFormat.child || dataFormat;
        if (dataFormat.unfold) {
            array.push('<ul>');
        } else if (dataFormat.topElement) {
            dataFormat.topElement = false;
            array.push('<ul>');
        } else {
            array.push('<ul style="display:none;">');
        }
        for (i = 0; i < data.length; i++) {
            single = data[i];
            if (typeof dataFormat.name == 'function') {
                name = dataFormat.name(single);
            } else if (typeof dataFormat.name == 'string') {
                name = single[dataFormat.name];
            } else {
                name = single['name'];
            }
            if (typeof dataFormat.childName == 'string') {
                children = single[dataFormat.childName];
            } else {
                children = single['children'];
            }

            if (typeof dataFormat.file == 'string') {
                file = single[dataFormat.file];
            } else {
                file = single['file'];
            }

            if (file) {
                array.push('<li>');
                array.push('<span class="file">');
            } else {
                array.push('<li class="collapsable"> <div class="hitarea collapsable-hitarea"></div>')
                array.push('<span class="folder">');
            }
            if (children && children.length > 0) {
                // if (dataFormat.unfold) {
                //     array.push('<i class="fa fa-minus-circle"></i>');
                // } else {
                //     array.push('<i class="fa fa-plus-circle"></i>');
                // }
                array.push(name);
                array.push('</span>');
                array.push(getTreeList(children, childDataFormat));
            } else {
                array.push(name);
                array.push('</span>');
            }
            array.push('</li>');
        }
        array.push('</ul>');
        console.log(array.join(''));
        return array.join('');
    }

    function getTreeListReal(data, dataFormat) {
        let i, single, name, file, children, childDataFormat,
            array = [];
        childDataFormat = dataFormat.child || dataFormat;
        if (dataFormat.unfold) {
            array.push('<ul>');
        } else if (dataFormat.topElement) {
            dataFormat.topElement = false;
            array.push('<ul>');
        } else {
            array.push('<ul style="display:none;">');
        }

        let fileTreeQueue = [];
        let lastIsFile = false;
        fileTreeQueue.push(data[0]);
        do {
            let single = fileTreeQueue.shift();
            if (typeof dataFormat.name == 'function') {
                name = dataFormat.name(single);
            } else if (typeof dataFormat.name == 'string') {
                name = single[dataFormat.name];
            } else {
                name = single['name'];
            }

            if (typeof dataFormat.childName == 'string') {
                children = single[dataFormat.childName];
            } else {
                children = single['children'];
            }

            if (typeof dataFormat.file == 'string') {
                file = single[dataFormat.file];
            } else {
                file = single['file'];
            }

            if (file) {
                array.push('<li>');
                array.push('<span class="file">');
                array.push(name);
                array.push('</span>');
                array.push('</li>');
            } else {
                if (lastIsFile) {
                    array.push('</ul>');
                    array.push('</li>');
                }

                array.push('<li class="collapsable"> <div class="hitarea collapsable-hitarea"></div>');
                array.push('<span class="folder">');
                array.push(name);
                array.push('</span>');
                array.push('<ul>');
                lastIsFile = true;
            }

            for (let tempIndex = 0; children && tempIndex < children.length; tempIndex++) {
                let temp = children[tempIndex];
                fileTreeQueue.push(temp);
            }
        } while(fileTreeQueue.length !== 0);

        array.push('</ul>');
        console.log(array.join(''));
        return array.join('');
    }

    initTreeJs(name);
}('tree'));